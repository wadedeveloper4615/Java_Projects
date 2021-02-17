package com.wade.decompiler.generic.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.AnnotationEntry;
import com.wade.decompiler.classfile.Annotations;
import com.wade.decompiler.classfile.Attribute;
import com.wade.decompiler.classfile.Code;
import com.wade.decompiler.classfile.CodeException;
import com.wade.decompiler.classfile.ExceptionTable;
import com.wade.decompiler.classfile.LineNumber;
import com.wade.decompiler.classfile.LineNumberTable;
import com.wade.decompiler.classfile.LocalVariable;
import com.wade.decompiler.classfile.LocalVariableTable;
import com.wade.decompiler.classfile.LocalVariableTypeTable;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.ParameterAnnotations;
import com.wade.decompiler.classfile.RuntimeVisibleParameterAnnotations;
import com.wade.decompiler.classfile.Utility;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generic.IINC;
import com.wade.decompiler.generic.NOP;
import com.wade.decompiler.generic.RET;
import com.wade.decompiler.generic.Select;
import com.wade.decompiler.generic.gen.AnnotationEntryGen;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.CodeExceptionGen;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.FieldGenOrMethodGen;
import com.wade.decompiler.generic.gen.LineNumberGen;
import com.wade.decompiler.generic.gen.LocalVariableGen;
import com.wade.decompiler.generic.gen.TargetLostException;
import com.wade.decompiler.util.BCELComparator;

public class MethodGen extends FieldGenOrMethodGen {
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(final Object o1, final Object o2) {
            final MethodGen THIS = (MethodGen) o1;
            final MethodGen THAT = (MethodGen) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(final Object o) {
            final MethodGen THIS = (MethodGen) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };

    static final class BranchStack {
        private final Stack<BranchTarget> branchTargets = new Stack<>();
        private final Hashtable<InstructionHandle, BranchTarget> visitedTargets = new Hashtable<>();

        public BranchTarget pop() {
            if (!branchTargets.empty()) {
                final BranchTarget bt = branchTargets.pop();
                return bt;
            }
            return null;
        }

        public void push(final InstructionHandle target, final int stackDepth) {
            if (visited(target)) {
                return;
            }
            branchTargets.push(visit(target, stackDepth));
        }

        private BranchTarget visit(final InstructionHandle target, final int stackDepth) {
            final BranchTarget bt = new BranchTarget(target, stackDepth);
            visitedTargets.put(target, bt);
            return bt;
        }

        private boolean visited(final InstructionHandle target) {
            return visitedTargets.get(target) != null;
        }
    }

    static final class BranchTarget {
        final InstructionHandle target;
        final int stackDepth;

        BranchTarget(final InstructionHandle target, final int stackDepth) {
            this.target = target;
            this.stackDepth = stackDepth;
        }
    }

    private String className;
    private Type[] argTypes;
    private String[] argNames;
    private int maxLocals;
    private int maxStack;
    private InstructionList il;
    private boolean stripAttributes;
    private LocalVariableTypeTable localVariableTypeTable = null;
    private final List<LocalVariableGen> variableList = new ArrayList<>();
    private final List<LineNumberGen> lineNumberList = new ArrayList<>();
    private final List<CodeExceptionGen> exceptionList = new ArrayList<>();
    private final List<String> throwsList = new ArrayList<>();
    private final List<Attribute> codeAttrsList = new ArrayList<>();
    private List<AnnotationEntryGen>[] paramAnnotations; // Array of lists containing AnnotationGen objects
    private boolean hasParameterAnnotations = false;
    private boolean haveUnpackedParameterAnnotations = false;
    private List<MethodObserver> observers;

    public MethodGen(final int access_flags, final Type return_type, final Type[] argTypes, String[] argNames, final String method_name, final String className, final InstructionList il, final ConstantPoolGen cp) {
        super(access_flags);
        setType(return_type);
        setArgumentTypes(argTypes);
        setArgumentNames(argNames);
        setName(method_name);
        setClassName(className);
        setInstructionList(il);
        setConstantPool(cp);
        final boolean abstract_ = isAbstract() || isNative();
        InstructionHandle start = null;
        final InstructionHandle end = null;
        if (!abstract_) {
            start = il.getStart();
            // end == null => live to end of method
            if (!isStatic() && (className != null)) { // Instance method -> `this' is local var 0
                addLocalVariable("this", ObjectType.getInstance(className), start, end);
            }
        }
        if (argTypes != null) {
            final int size = argTypes.length;
            for (final Type arg_type : argTypes) {
                if (Type.VOID == arg_type) {
                    throw new ClassGenException("'void' is an illegal argument type for a method");
                }
            }
            if (argNames != null) { // Names for variables provided?
                if (size != argNames.length) {
                    throw new ClassGenException("Mismatch in argument array lengths: " + size + " vs. " + argNames.length);
                }
            } else { // Give them dummy names
                argNames = new String[size];
                for (int i = 0; i < size; i++) {
                    argNames[i] = "arg" + i;
                }
                setArgumentNames(argNames);
            }
            if (!abstract_) {
                for (int i = 0; i < size; i++) {
                    addLocalVariable(argNames[i], argTypes[i], start, end);
                }
            }
        }
    }

    public MethodGen(final Method method, final String className, final ConstantPoolGen cp) {
        this(method.getFlags(), Type.getReturnType(method.getSignature()), Type.getArgumentTypes(method.getSignature()), null, method.getName(), className, ((method.getFlags() & (ClassAccessFlags.ACC_ABSTRACT.getFlag() | ClassAccessFlags.ACC_NATIVE.getFlag())) == 0) ? new InstructionList(getByteCodes(method)) : null, cp);
        final Attribute[] attributes = method.getAttributes();
        for (final Attribute attribute : attributes) {
            Attribute a = attribute;
            if (a instanceof Code) {
                final Code c = (Code) a;
                setMaxStack(c.getMaxStack());
                setMaxLocals(c.getMaxLocals());
                final CodeException[] ces = c.getExceptionTable();
                if (ces != null) {
                    for (final CodeException ce : ces) {
                        final int type = ce.getCatchType();
                        ObjectType c_type = null;
                        if (type > 0) {
                            final String cen = method.getConstantPool().getConstantString(type, ClassFileConstants.CONSTANT_Class);
                            c_type = ObjectType.getInstance(cen);
                        }
                        final int end_pc = ce.getEndPC();
                        final int length = getByteCodes(method).length;
                        InstructionHandle end;
                        if (length == end_pc) { // May happen, because end_pc is exclusive
                            end = il.getEnd();
                        } else {
                            end = il.findHandle(end_pc);
                            end = end.getPrev(); // Make it inclusive
                        }
                        addExceptionHandler(il.findHandle(ce.getStartPC()), end, il.findHandle(ce.getHandlerPC()), c_type);
                    }
                }
                final Attribute[] c_attributes = c.getAttributes();
                for (final Attribute c_attribute : c_attributes) {
                    a = c_attribute;
                    if (a instanceof LineNumberTable) {
                        final LineNumber[] ln = ((LineNumberTable) a).getLineNumberTable();
                        for (final LineNumber l : ln) {
                            final InstructionHandle ih = il.findHandle(l.getStartPC());
                            if (ih != null) {
                                addLineNumber(ih, l.getLineNumber());
                            }
                        }
                    } else if (a instanceof LocalVariableTable) {
                        updateLocalVariableTable((LocalVariableTable) a);
                    } else if (a instanceof LocalVariableTypeTable) {
                        this.localVariableTypeTable = (LocalVariableTypeTable) a.copy(cp.getConstantPool());
                    } else {
                        addCodeAttribute(a);
                    }
                }
            } else if (a instanceof ExceptionTable) {
                final String[] names = ((ExceptionTable) a).getExceptionNames();
                for (final String name2 : names) {
                    addException(name2);
                }
            } else if (a instanceof Annotations) {
                final Annotations runtimeAnnotations = (Annotations) a;
                final AnnotationEntry[] aes = runtimeAnnotations.getAnnotationEntries();
                for (final AnnotationEntry element : aes) {
                    addAnnotationEntry(new AnnotationEntryGen(element, cp, false));
                }
            } else {
                addAttribute(a);
            }
        }
    }

    public void addAnnotationsAsAttribute(final ConstantPoolGen cp) {
        final Attribute[] attrs = AnnotationEntryGen.getAnnotationAttributes(cp, super.getAnnotationEntries());
        for (final Attribute attr : attrs) {
            addAttribute(attr);
        }
    }

    public void addCodeAttribute(final Attribute a) {
        codeAttrsList.add(a);
    }

    public void addException(final String className) {
        throwsList.add(className);
    }

    public CodeExceptionGen addExceptionHandler(final InstructionHandle start_pc, final InstructionHandle end_pc, final InstructionHandle handler_pc, final ObjectType catch_type) {
        if ((start_pc == null) || (end_pc == null) || (handler_pc == null)) {
            throw new ClassGenException("Exception handler target is null instruction");
        }
        final CodeExceptionGen c = new CodeExceptionGen(start_pc, end_pc, handler_pc, catch_type);
        exceptionList.add(c);
        return c;
    }

    public LineNumberGen addLineNumber(final InstructionHandle ih, final int srcLine) {
        final LineNumberGen l = new LineNumberGen(ih, srcLine);
        lineNumberList.add(l);
        return l;
    }

    public LocalVariableGen addLocalVariable(final String name, final Type type, final InstructionHandle start, final InstructionHandle end) {
        return addLocalVariable(name, type, maxLocals, start, end);
    }

    public LocalVariableGen addLocalVariable(final String name, final Type type, final int slot, final InstructionHandle start, final InstructionHandle end) {
        return addLocalVariable(name, type, slot, start, end, slot);
    }

    public LocalVariableGen addLocalVariable(final String name, final Type type, final int slot, final InstructionHandle start, final InstructionHandle end, final int orig_index) {
        final byte t = type.getType();
        if (t != Const.T_ADDRESS) {
            final int add = type.getSize();
            if (slot + add > maxLocals) {
                maxLocals = slot + add;
            }
            final LocalVariableGen l = new LocalVariableGen(slot, name, type, start, end, orig_index);
            int i;
            if ((i = variableList.indexOf(l)) >= 0) {
                variableList.set(i, l);
            } else {
                variableList.add(l);
            }
            return l;
        }
        throw new IllegalArgumentException("Can not use " + type + " as type for local variable");
    }

    public void addObserver(final MethodObserver o) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(o);
    }

    public void addParameterAnnotation(final int parameterIndex, final AnnotationEntryGen annotation) {
        ensureExistingParameterAnnotationsUnpacked();
        if (!hasParameterAnnotations) {
            @SuppressWarnings("unchecked") // OK
            final List<AnnotationEntryGen>[] parmList = new List[argTypes.length];
            paramAnnotations = parmList;
            hasParameterAnnotations = true;
        }
        final List<AnnotationEntryGen> existingAnnotations = paramAnnotations[parameterIndex];
        if (existingAnnotations != null) {
            existingAnnotations.add(annotation);
        } else {
            final List<AnnotationEntryGen> l = new ArrayList<>();
            l.add(annotation);
            paramAnnotations[parameterIndex] = l;
        }
    }

    public void addParameterAnnotationsAsAttribute(final ConstantPoolGen cp) {
        if (!hasParameterAnnotations) {
            return;
        }
        final Attribute[] attrs = AnnotationEntryGen.getParameterAnnotationAttributes(cp, paramAnnotations);
        if (attrs != null) {
            for (final Attribute attr : attrs) {
                addAttribute(attr);
            }
        }
    }

    private Attribute[] addRuntimeAnnotationsAsAttribute(final ConstantPoolGen cp) {
        final Attribute[] attrs = AnnotationEntryGen.getAnnotationAttributes(cp, super.getAnnotationEntries());
        for (final Attribute attr : attrs) {
            addAttribute(attr);
        }
        return attrs;
    }

    private Attribute[] addRuntimeParameterAnnotationsAsAttribute(final ConstantPoolGen cp) {
        if (!hasParameterAnnotations) {
            return new Attribute[0];
        }
        final Attribute[] attrs = AnnotationEntryGen.getParameterAnnotationAttributes(cp, paramAnnotations);
        for (final Attribute attr : attrs) {
            addAttribute(attr);
        }
        return attrs;
    }

    private void adjustLocalVariableTypeTable(final LocalVariableTable lvt) {
        final LocalVariable[] lv = lvt.getLocalVariableTable();
        final LocalVariable[] lvg = localVariableTypeTable.getLocalVariableTypeTable();
        for (final LocalVariable element : lvg) {
            for (final LocalVariable l : lv) {
                if (element.getName().equals(l.getName()) && element.getIndex() == l.getOrigIndex()) {
                    element.setLength(l.getLength());
                    element.setStartPC(l.getStartPC());
                    element.setIndex(l.getIndex());
                    break;
                }
            }
        }
    }

    public MethodGen copy(final String className, final ConstantPoolGen cp) {
        final Method m = ((MethodGen) clone()).getMethod();
        final MethodGen mg = new MethodGen(m, className, super.getConstantPool());
        if (super.getConstantPool() != cp) {
            mg.setConstantPool(cp);
            mg.getInstructionList().replaceConstantPool(super.getConstantPool(), cp);
        }
        return mg;
    }

    private void ensureExistingParameterAnnotationsUnpacked() {
        if (haveUnpackedParameterAnnotations) {
            return;
        }
        // Find attributes that contain parameter annotation data
        final Attribute[] attrs = getAttributes();
        ParameterAnnotations paramAnnVisAttr = null;
        ParameterAnnotations paramAnnInvisAttr = null;
        for (final Attribute attribute : attrs) {
            if (attribute instanceof ParameterAnnotations) {
                // Initialize paramAnnotations
                if (!hasParameterAnnotations) {
                    @SuppressWarnings("unchecked") // OK
                    final List<AnnotationEntryGen>[] parmList = new List[argTypes.length];
                    paramAnnotations = parmList;
                    for (int j = 0; j < argTypes.length; j++) {
                        paramAnnotations[j] = new ArrayList<>();
                    }
                }
                hasParameterAnnotations = true;
                final ParameterAnnotations rpa = (ParameterAnnotations) attribute;
                if (rpa instanceof RuntimeVisibleParameterAnnotations) {
                    paramAnnVisAttr = rpa;
                } else {
                    paramAnnInvisAttr = rpa;
                }
                final ParameterAnnotationEntry[] parameterAnnotationEntries = rpa.getParameterAnnotationEntries();
                for (int j = 0; j < parameterAnnotationEntries.length; j++) {
                    // This returns Annotation[] ...
                    final ParameterAnnotationEntry immutableArray = rpa.getParameterAnnotationEntries()[j];
                    // ... which needs transforming into an AnnotationGen[] ...
                    final List<AnnotationEntryGen> mutable = makeMutableVersion(immutableArray.getAnnotationEntries());
                    // ... then add these to any we already know about
                    paramAnnotations[j].addAll(mutable);
                }
            }
        }
        if (paramAnnVisAttr != null) {
            removeAttribute(paramAnnVisAttr);
        }
        if (paramAnnInvisAttr != null) {
            removeAttribute(paramAnnInvisAttr);
        }
        haveUnpackedParameterAnnotations = true;
    }

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public List<AnnotationEntryGen> getAnnotationsOnParameter(final int i) {
        ensureExistingParameterAnnotationsUnpacked();
        if (!hasParameterAnnotations || i > argTypes.length) {
            return null;
        }
        return paramAnnotations[i];
    }

    public String getArgumentName(final int i) {
        return argNames[i];
    }

    public String[] getArgumentNames() {
        return argNames.clone();
    }

    public Type getArgumentType(final int i) {
        return argTypes[i];
    }

    public Type[] getArgumentTypes() {
        return argTypes.clone();
    }

    public String getClassName() {
        return className;
    }

    public Attribute[] getCodeAttributes() {
        final Attribute[] attributes = new Attribute[codeAttrsList.size()];
        codeAttrsList.toArray(attributes);
        return attributes;
    }

    private CodeException[] getCodeExceptions() {
        final int size = exceptionList.size();
        final CodeException[] c_exc = new CodeException[size];
        for (int i = 0; i < size; i++) {
            final CodeExceptionGen c = exceptionList.get(i);
            c_exc[i] = c.getCodeException(super.getConstantPool());
        }
        return c_exc;
    }

    public CodeExceptionGen[] getExceptionHandlers() {
        final CodeExceptionGen[] cg = new CodeExceptionGen[exceptionList.size()];
        exceptionList.toArray(cg);
        return cg;
    }

    public String[] getExceptions() {
        final String[] e = new String[throwsList.size()];
        throwsList.toArray(e);
        return e;
    }

    private ExceptionTable getExceptionTable(final ConstantPoolGen cp) {
        final int size = throwsList.size();
        final int[] ex = new int[size];
        for (int i = 0; i < size; i++) {
            ex[i] = cp.addClass(throwsList.get(i));
        }
        return new ExceptionTable(cp.addUtf8("Exceptions"), 2 + 2 * size, ex, cp.getConstantPool());
    }

    public InstructionList getInstructionList() {
        return il;
    }

    public LineNumberGen[] getLineNumbers() {
        final LineNumberGen[] lg = new LineNumberGen[lineNumberList.size()];
        lineNumberList.toArray(lg);
        return lg;
    }

    public LineNumberTable getLineNumberTable(final ConstantPoolGen cp) {
        final int size = lineNumberList.size();
        final LineNumber[] ln = new LineNumber[size];
        for (int i = 0; i < size; i++) {
            ln[i] = lineNumberList.get(i).getLineNumber();
        }
        return new LineNumberTable(cp.addUtf8("LineNumberTable"), 2 + ln.length * 4, ln, cp.getConstantPool());
    }

    public LocalVariableGen[] getLocalVariables() {
        final int size = variableList.size();
        final LocalVariableGen[] lg = new LocalVariableGen[size];
        variableList.toArray(lg);
        for (int i = 0; i < size; i++) {
            if ((lg[i].getStart() == null) && (il != null)) {
                lg[i].setStart(il.getStart());
            }
            if ((lg[i].getEnd() == null) && (il != null)) {
                lg[i].setEnd(il.getEnd());
            }
        }
        if (size > 1) {
            Arrays.sort(lg, (o1, o2) -> o1.getIndex() - o2.getIndex());
        }
        return lg;
    }

    public LocalVariableTable getLocalVariableTable(final ConstantPoolGen cp) {
        final LocalVariableGen[] lg = getLocalVariables();
        final int size = lg.length;
        final LocalVariable[] lv = new LocalVariable[size];
        for (int i = 0; i < size; i++) {
            lv[i] = lg[i].getLocalVariable(cp);
        }
        return new LocalVariableTable(cp.addUtf8("LocalVariableTable"), 2 + lv.length * 10, lv, cp.getConstantPool());
    }

    public LocalVariableTypeTable getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public Method getMethod() {
        final String signature = getSignature();
        final ConstantPoolGen _cp = super.getConstantPool();
        final int name_index = _cp.addUtf8(super.getName());
        final int signature_index = _cp.addUtf8(signature);
        byte[] byte_code = null;
        if (il != null) {
            byte_code = il.getByteCode();
        }
        LineNumberTable lnt = null;
        LocalVariableTable lvt = null;
        if ((variableList.size() > 0) && !stripAttributes) {
            updateLocalVariableTable(getLocalVariableTable(_cp));
            addCodeAttribute(lvt = getLocalVariableTable(_cp));
        }
        if (localVariableTypeTable != null) {
            // LocalVariable length in LocalVariableTypeTable is not updated automatically.
            // It's a difference with LocalVariableTable.
            if (lvt != null) {
                adjustLocalVariableTypeTable(lvt);
            }
            addCodeAttribute(localVariableTypeTable);
        }
        if ((lineNumberList.size() > 0) && !stripAttributes) {
            addCodeAttribute(lnt = getLineNumberTable(_cp));
        }
        final Attribute[] code_attrs = getCodeAttributes();
        int attrs_len = 0;
        for (final Attribute code_attr : code_attrs) {
            attrs_len += code_attr.getLength() + 6;
        }
        final CodeException[] c_exc = getCodeExceptions();
        final int exc_len = c_exc.length * 8; // Every entry takes 8 bytes
        Code code = null;
        if ((il != null) && !isAbstract() && !isNative()) {
            // Remove any stale code attribute
            final Attribute[] attributes = getAttributes();
            for (final Attribute a : attributes) {
                if (a instanceof Code) {
                    removeAttribute(a);
                }
            }
            code = new Code(_cp.addUtf8("Code"), 8 + byte_code.length + // prologue byte code
                    2 + exc_len + // exceptions
                    2 + attrs_len, // attributes
                    maxStack, maxLocals, byte_code, c_exc, code_attrs, _cp.getConstantPool());
            addAttribute(code);
        }
        final Attribute[] annotations = addRuntimeAnnotationsAsAttribute(_cp);
        final Attribute[] parameterAnnotations = addRuntimeParameterAnnotationsAsAttribute(_cp);
        ExceptionTable et = null;
        if (throwsList.size() > 0) {
            addAttribute(et = getExceptionTable(_cp));
            // Add `Exceptions' if there are "throws" clauses
        }
        final Method m = new Method(super.getFlags(), name_index, signature_index, getAttributes(), _cp.getConstantPool());
        // Undo effects of adding attributes
        if (lvt != null) {
            removeCodeAttribute(lvt);
        }
        if (localVariableTypeTable != null) {
            removeCodeAttribute(localVariableTypeTable);
        }
        if (lnt != null) {
            removeCodeAttribute(lnt);
        }
        if (code != null) {
            removeAttribute(code);
        }
        if (et != null) {
            removeAttribute(et);
        }
        removeRuntimeAttributes(annotations);
        removeRuntimeAttributes(parameterAnnotations);
        return m;
    }

    public Type getReturnType() {
        return getType();
    }

    @Override
    public String getSignature() {
        return Type.getMethodSignature(super.getType(), argTypes);
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    private List<AnnotationEntryGen> makeMutableVersion(final AnnotationEntry[] mutableArray) {
        final List<AnnotationEntryGen> result = new ArrayList<>();
        for (final AnnotationEntry element : mutableArray) {
            result.add(new AnnotationEntryGen(element, getConstantPool(), false));
        }
        return result;
    }

    public void removeCodeAttribute(final Attribute a) {
        codeAttrsList.remove(a);
    }

    public void removeCodeAttributes() {
        localVariableTypeTable = null;
        codeAttrsList.clear();
    }

    public void removeException(final String c) {
        throwsList.remove(c);
    }

    public void removeExceptionHandler(final CodeExceptionGen c) {
        exceptionList.remove(c);
    }

    public void removeExceptionHandlers() {
        exceptionList.clear();
    }

    public void removeExceptions() {
        throwsList.clear();
    }

    public void removeLineNumber(final LineNumberGen l) {
        lineNumberList.remove(l);
    }

    public void removeLineNumbers() {
        lineNumberList.clear();
    }

    public void removeLocalVariable(final LocalVariableGen l) {
        l.dispose();
        variableList.remove(l);
    }

    public void removeLocalVariables() {
        for (final LocalVariableGen lv : variableList) {
            lv.dispose();
        }
        variableList.clear();
    }

    public void removeLocalVariableTypeTable() {
        localVariableTypeTable = null;
    }

    public void removeNOPs() {
        if (il != null) {
            InstructionHandle next;
            for (InstructionHandle ih = il.getStart(); ih != null; ih = next) {
                next = ih.getNext();
                if ((next != null) && (ih.getInstruction() instanceof NOP)) {
                    try {
                        il.delete(ih);
                    } catch (final TargetLostException e) {
                        for (final InstructionHandle target : e.getTargets()) {
                            for (final InstructionTargeter targeter : target.getTargeters()) {
                                targeter.updateTarget(target, next);
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeObserver(final MethodObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

    public void removeRuntimeAttributes(final Attribute[] attrs) {
        for (final Attribute attr : attrs) {
            removeAttribute(attr);
        }
    }

    public void setArgumentName(final int i, final String name) {
        argNames[i] = name;
    }

    public void setArgumentNames(final String[] arg_names) {
        this.argNames = arg_names;
    }

    public void setArgumentType(final int i, final Type type) {
        argTypes[i] = type;
    }

    public void setArgumentTypes(final Type[] arg_types) {
        this.argTypes = arg_types;
    }

    public void setClassName(final String class_name) { // TODO could be package-protected?
        this.className = class_name;
    }

    public void setInstructionList(final InstructionList il) { // TODO could be package-protected?
        this.il = il;
    }

    public void setMaxLocals() { // TODO could be package-protected? (some tests would need repackaging)
        if (il != null) {
            int max = isStatic() ? 0 : 1;
            if (argTypes != null) {
                for (final Type arg_type : argTypes) {
                    max += arg_type.getSize();
                }
            }
            for (InstructionHandle ih = il.getStart(); ih != null; ih = ih.getNext()) {
                final Instruction ins = ih.getInstruction();
                if ((ins instanceof LocalVariableInstruction) || (ins instanceof RET) || (ins instanceof IINC)) {
                    final int index = ((IndexedInstruction) ins).getIndex() + ((TypedInstruction) ins).getType(super.getConstantPool()).getSize();
                    if (index > max) {
                        max = index;
                    }
                }
            }
            maxLocals = max;
        } else {
            maxLocals = 0;
        }
    }

    public void setMaxLocals(final int m) {
        maxLocals = m;
    }

    public void setMaxStack() { // TODO could be package-protected? (some tests would need repackaging)
        if (il != null) {
            maxStack = getMaxStack(super.getConstantPool(), il, getExceptionHandlers());
        } else {
            maxStack = 0;
        }
    }

    public void setMaxStack(final int m) { // TODO could be package-protected?
        maxStack = m;
    }

    public void setReturnType(final Type return_type) {
        setType(return_type);
    }
    // J5TODO: Should paramAnnotations be an array of arrays? Rather than an array
    // of lists, this
    // is more likely to suggest to the caller it is readonly (which a List does
    // not).

    public void stripAttributes(final boolean flag) {
        stripAttributes = flag;
    }

    @Override
    public final String toString() {
        final String access = Utility.accessToString(new ClassAccessFlagsList(super.getFlags()));
        String signature = Type.getMethodSignature(super.getType(), argTypes);
        signature = Utility.methodSignatureToString(signature, super.getName(), access, true, getLocalVariableTable(super.getConstantPool()));
        final StringBuilder buf = new StringBuilder(signature);
        for (final Attribute a : getAttributes()) {
            if (!((a instanceof Code) || (a instanceof ExceptionTable))) {
                buf.append(" [").append(a).append("]");
            }
        }
        if (throwsList.size() > 0) {
            for (final String throwsDescriptor : throwsList) {
                buf.append("\n\t\tthrows ").append(throwsDescriptor);
            }
        }
        return buf.toString();
    }

    public void update() {
        if (observers != null) {
            for (final MethodObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    private void updateLocalVariableTable(final LocalVariableTable a) {
        final LocalVariable[] lv = a.getLocalVariableTable();
        removeLocalVariables();
        for (final LocalVariable l : lv) {
            InstructionHandle start = il.findHandle(l.getStartPC());
            final InstructionHandle end = il.findHandle(l.getStartPC() + l.getLength());
            // Repair malformed handles
            if (null == start) {
                start = il.getStart();
            }
            // end == null => live to end of method
            // Since we are recreating the LocalVaraible, we must
            // propagate the orig_index to new copy.
            addLocalVariable(l.getName(), Type.getType(l.getSignature()), l.getIndex(), start, end, l.getOrigIndex());
        }
    }

    private static byte[] getByteCodes(final Method method) {
        final Code code = method.getCode();
        if (code == null) {
            throw new IllegalStateException(String.format("The method '%s' has no code.", method));
        }
        return code.getCode();
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static int getMaxStack(final ConstantPoolGen cp, final InstructionList il, final CodeExceptionGen[] et) {
        final BranchStack branchTargets = new BranchStack();
        for (final CodeExceptionGen element : et) {
            final InstructionHandle handler_pc = element.getHandlerPC();
            if (handler_pc != null) {
                branchTargets.push(handler_pc, 1);
            }
        }
        int stackDepth = 0;
        int maxStackDepth = 0;
        InstructionHandle ih = il.getStart();
        while (ih != null) {
            final Instruction instruction = ih.getInstruction();
            final short opcode = instruction.getOpcode();
            final int delta = instruction.produceStack(cp) - instruction.consumeStack(cp);
            stackDepth += delta;
            if (stackDepth > maxStackDepth) {
                maxStackDepth = stackDepth;
            }
            // choose the next instruction based on whether current is a branch.
            if (instruction instanceof BranchInstruction) {
                final BranchInstruction branch = (BranchInstruction) instruction;
                if (instruction instanceof Select) {
                    // explore all of the select's targets. the default target is handled below.
                    final Select select = (Select) branch;
                    final InstructionHandle[] targets = select.getTargets();
                    for (final InstructionHandle target : targets) {
                        branchTargets.push(target, stackDepth);
                    }
                    // nothing to fall through to.
                    ih = null;
                } else if (!(branch instanceof IfInstruction)) {
                    // if an instruction that comes back to following PC,
                    // push next instruction, with stack depth reduced by 1.
                    if (opcode == Const.JSR || opcode == Const.JSR_W) {
                        branchTargets.push(ih.getNext(), stackDepth - 1);
                    }
                    ih = null;
                }
                // for all branches, the target of the branch is pushed on the branch stack.
                // conditional branches have a fall through case, selects don't, and
                // jsr/jsr_w return to the next instruction.
                branchTargets.push(branch.getTarget(), stackDepth);
            } else // check for instructions that terminate the method.
            if (opcode == Const.ATHROW || opcode == Const.RET || (opcode >= Const.IRETURN && opcode <= Const.RETURN)) {
                ih = null;
            }
            // normal case, go to the next instruction.
            if (ih != null) {
                ih = ih.getNext();
            }
            // if we have no more instructions, see if there are any deferred branches to
            // explore.
            if (ih == null) {
                final BranchTarget bt = branchTargets.pop();
                if (bt != null) {
                    ih = bt.target;
                    stackDepth = bt.stackDepth;
                }
            }
        }
        return maxStackDepth;
    }

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
