package org.apache.bcel.generic.gen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import org.apache.bcel.ClassFileName;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.annotations.ParameterAnnotationEntry;
import org.apache.bcel.classfile.annotations.RuntimeVisibleParameterAnnotations;
import org.apache.bcel.classfile.attribute.Annotations;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.Code;
import org.apache.bcel.classfile.attribute.ExceptionTable;
import org.apache.bcel.classfile.attribute.LineNumberTable;
import org.apache.bcel.classfile.attribute.LocalVariableTable;
import org.apache.bcel.classfile.attribute.LocalVariableTypeTable;
import org.apache.bcel.classfile.attribute.ParameterAnnotations;
import org.apache.bcel.enums.ClassAccessFlags;
import org.apache.bcel.enums.ClassFileConstants;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.exceptions.ClassFormatException;
import org.apache.bcel.generic.IINC;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.RET;
import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.IndexedInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.LocalVariableInstruction;
import org.apache.bcel.generic.base.MethodObserver;
import org.apache.bcel.generic.base.TargetLostException;
import org.apache.bcel.generic.base.TypedInstruction;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionList;
import org.apache.bcel.generic.control.InstructionTargeter;
import org.apache.bcel.util.BCELComparator;

public class MethodGen extends FieldGenOrMethodGen {
    static class BranchStack {
        private Stack<BranchTarget> branchTargets = new Stack<>();
        private Hashtable<InstructionHandle, BranchTarget> visitedTargets = new Hashtable<>();

        public BranchTarget pop() {
            if (!branchTargets.empty()) {
                BranchTarget bt = branchTargets.pop();
                return bt;
            }
            return null;
        }

        public void push(InstructionHandle target, int stackDepth) {
            if (visited(target)) {
                return;
            }
            branchTargets.push(visit(target, stackDepth));
        }

        private BranchTarget visit(InstructionHandle target, int stackDepth) {
            BranchTarget bt = new BranchTarget(target, stackDepth);
            visitedTargets.put(target, bt);
            return bt;
        }

        private boolean visited(InstructionHandle target) {
            return visitedTargets.get(target) != null;
        }
    }

    static class BranchTarget {
        InstructionHandle target;
        int stackDepth;

        BranchTarget(InstructionHandle target, int stackDepth) {
            this.target = target;
            this.stackDepth = stackDepth;
        }
    }

    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(Object o1, Object o2) {
            MethodGen THIS = (MethodGen) o1;
            MethodGen THAT = (MethodGen) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(Object o) {
            MethodGen THIS = (MethodGen) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };
    private ClassFileName className;
    private Type[] argTypes;
    private String[] argNames;
    private int maxLocals;
    private int maxStack;
    private InstructionList il;
    private boolean stripAttributes;
    private LocalVariableTypeTable localVariableTypeTable = null;
    private List<LocalVariableGen> variableList = new ArrayList<>();
    private List<LineNumberGen> lineNumberList = new ArrayList<>();
    private List<CodeExceptionGen> exceptionList = new ArrayList<>();
    private List<String> throwsList = new ArrayList<>();
    private List<Attribute> codeAttrsList = new ArrayList<>();
    private List<AnnotationEntryGen>[] paramAnnotations;
    private boolean hasParameterAnnotations = false;
    private boolean haveUnpackedParameterAnnotations = false;
    private List<MethodObserver> observers;

    public MethodGen(int access_flags, Type return_type, Type[] argTypes, String[] argNames, String method_name, ClassFileName className, InstructionList il, ConstantPoolGen cp) {
        super(access_flags);
        setType(return_type);
        setArgumentTypes(argTypes);
        setArgumentNames(argNames);
        setName(method_name);
        setClassName(className);
        setInstructionList(il);
        setConstantPool(cp);
        boolean abstract_ = isAbstract() || isNative();
        InstructionHandle start = null;
        InstructionHandle end = null;
        if (!abstract_) {
            start = il.getStart();
            if (!isStatic() && (className != null)) {
                addLocalVariable("this", ObjectType.getInstance(className.getName()), start, end);
            }
        }
        if (argTypes != null) {
            int size = argTypes.length;
            for (Type arg_type : argTypes) {
                if (Type.VOID == arg_type) {
                    throw new ClassGenException("'void' is an illegal argument type for a method");
                }
            }
            if (argNames != null) {
                if (size != argNames.length) {
                    throw new ClassGenException("Mismatch in argument array lengths: " + size + " vs. " + argNames.length);
                }
            } else {
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

    public MethodGen(Method method, ClassFileName className, ConstantPoolGen cp) {
        this(method.getAccessFlags(), Type.getReturnType(method.getSignature()), Type.getArgumentTypes(method.getSignature()), null, method.getName(), className, ((method.getAccessFlags() & (ClassAccessFlags.ACC_ABSTRACT.getFlag() | ClassAccessFlags.ACC_NATIVE.getFlag())) == 0) ? new InstructionList(getByteCodes(method)) : null, cp);
        Attribute[] attributes = method.getAttributes();
        for (Attribute attribute : attributes) {
            Attribute a = attribute;
            if (a instanceof Code) {
                Code c = (Code) a;
                setMaxStack(c.getMaxStack());
                setMaxLocals(c.getMaxLocals());
                CodeException[] ces = c.getExceptionTable();
                if (ces != null) {
                    for (CodeException ce : ces) {
                        int type = ce.getCatchType();
                        ObjectType c_type = null;
                        if (type > 0) {
                            String cen = method.getConstantPool().getConstantString(type, ClassFileConstants.CONSTANT_Class);
                            c_type = ObjectType.getInstance(cen);
                        }
                        int end_pc = ce.getEndPC();
                        int length = getByteCodes(method).length;
                        InstructionHandle end;
                        if (length == end_pc) {
                            end = il.getEnd();
                        } else {
                            end = il.findHandle(end_pc);
                            end = end.getPrev();
                        }
                        addExceptionHandler(il.findHandle(ce.getStartPC()), end, il.findHandle(ce.getHandlerPC()), c_type);
                    }
                }
                Attribute[] c_attributes = c.getAttributes();
                for (Attribute c_attribute : c_attributes) {
                    a = c_attribute;
                    if (a instanceof LineNumberTable) {
                        LineNumber[] ln = ((LineNumberTable) a).getLineNumberTable();
                        for (LineNumber l : ln) {
                            InstructionHandle ih = il.findHandle(l.getStartPC());
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
                String[] names = ((ExceptionTable) a).getExceptionNames();
                for (String name2 : names) {
                    addException(name2);
                }
            } else if (a instanceof Annotations) {
                Annotations runtimeAnnotations = (Annotations) a;
                AnnotationEntry[] aes = runtimeAnnotations.getAnnotationEntries();
                for (AnnotationEntry element : aes) {
                    addAnnotationEntry(new AnnotationEntryGen(element, cp, false));
                }
            } else {
                addAttribute(a);
            }
        }
    }

    public void addAnnotationsAsAttribute(ConstantPoolGen cp) {
        Attribute[] attrs = AnnotationEntryGen.getAnnotationAttributes(cp, super.getAnnotationEntries());
        for (Attribute attr : attrs) {
            addAttribute(attr);
        }
    }

    public void addCodeAttribute(Attribute a) {
        codeAttrsList.add(a);
    }

    public void addException(String className) {
        throwsList.add(className);
    }

    public CodeExceptionGen addExceptionHandler(InstructionHandle start_pc, InstructionHandle end_pc, InstructionHandle handler_pc, ObjectType catch_type) {
        if ((start_pc == null) || (end_pc == null) || (handler_pc == null)) {
            throw new ClassGenException("Exception handler target is null instruction");
        }
        CodeExceptionGen c = new CodeExceptionGen(start_pc, end_pc, handler_pc, catch_type);
        exceptionList.add(c);
        return c;
    }

    public LineNumberGen addLineNumber(InstructionHandle ih, int srcLine) {
        LineNumberGen l = new LineNumberGen(ih, srcLine);
        lineNumberList.add(l);
        return l;
    }

    public LocalVariableGen addLocalVariable(String name, Type type, InstructionHandle start, InstructionHandle end) {
        return addLocalVariable(name, type, maxLocals, start, end);
    }

    public LocalVariableGen addLocalVariable(String name, Type type, int slot, InstructionHandle start, InstructionHandle end) {
        return addLocalVariable(name, type, slot, start, end, slot);
    }

    public LocalVariableGen addLocalVariable(String name, Type type, int slot, InstructionHandle start, InstructionHandle end, int orig_index) {
        byte t = type.getType();
        if (t != Const.T_ADDRESS) {
            int add = type.getSize();
            if (slot + add > maxLocals) {
                maxLocals = slot + add;
            }
            LocalVariableGen l = new LocalVariableGen(slot, name, type, start, end, orig_index);
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

    public void addObserver(MethodObserver o) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(o);
    }

    public void addParameterAnnotation(int parameterIndex, AnnotationEntryGen annotation) {
        ensureExistingParameterAnnotationsUnpacked();
        if (!hasParameterAnnotations) {
            @SuppressWarnings("unchecked")
            List<AnnotationEntryGen>[] parmList = new List[argTypes.length];
            paramAnnotations = parmList;
            hasParameterAnnotations = true;
        }
        List<AnnotationEntryGen> existingAnnotations = paramAnnotations[parameterIndex];
        if (existingAnnotations != null) {
            existingAnnotations.add(annotation);
        } else {
            List<AnnotationEntryGen> l = new ArrayList<>();
            l.add(annotation);
            paramAnnotations[parameterIndex] = l;
        }
    }

    public void addParameterAnnotationsAsAttribute(ConstantPoolGen cp) {
        if (!hasParameterAnnotations) {
            return;
        }
        Attribute[] attrs = AnnotationEntryGen.getParameterAnnotationAttributes(cp, paramAnnotations);
        if (attrs != null) {
            for (Attribute attr : attrs) {
                addAttribute(attr);
            }
        }
    }

    private Attribute[] addRuntimeAnnotationsAsAttribute(ConstantPoolGen cp) {
        Attribute[] attrs = AnnotationEntryGen.getAnnotationAttributes(cp, super.getAnnotationEntries());
        for (Attribute attr : attrs) {
            addAttribute(attr);
        }
        return attrs;
    }

    private Attribute[] addRuntimeParameterAnnotationsAsAttribute(ConstantPoolGen cp) {
        if (!hasParameterAnnotations) {
            return new Attribute[0];
        }
        Attribute[] attrs = AnnotationEntryGen.getParameterAnnotationAttributes(cp, paramAnnotations);
        for (Attribute attr : attrs) {
            addAttribute(attr);
        }
        return attrs;
    }

    private void adjustLocalVariableTypeTable(LocalVariableTable lvt) {
        LocalVariable[] lv = lvt.getLocalVariableTable();
        LocalVariable[] lvg = localVariableTypeTable.getLocalVariableTypeTable();
        for (LocalVariable element : lvg) {
            for (LocalVariable l : lv) {
                if (element.getName().equals(l.getName()) && element.getIndex() == l.getOrigIndex()) {
                    element.setLength(l.getLength());
                    element.setStartPC(l.getStartPC());
                    element.setIndex(l.getIndex());
                    break;
                }
            }
        }
    }

    public MethodGen copy(ClassFileName className, ConstantPoolGen cp) throws ClassFormatException, IOException {
        Method m = ((MethodGen) clone()).getMethod();
        MethodGen mg = new MethodGen(m, className, super.getConstantPool());
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
        Attribute[] attrs = getAttributes();
        ParameterAnnotations paramAnnVisAttr = null;
        ParameterAnnotations paramAnnInvisAttr = null;
        for (Attribute attribute : attrs) {
            if (attribute instanceof ParameterAnnotations) {
                if (!hasParameterAnnotations) {
                    @SuppressWarnings("unchecked")
                    List<AnnotationEntryGen>[] parmList = new List[argTypes.length];
                    paramAnnotations = parmList;
                    for (int j = 0; j < argTypes.length; j++) {
                        paramAnnotations[j] = new ArrayList<>();
                    }
                }
                hasParameterAnnotations = true;
                ParameterAnnotations rpa = (ParameterAnnotations) attribute;
                if (rpa instanceof RuntimeVisibleParameterAnnotations) {
                    paramAnnVisAttr = rpa;
                } else {
                    paramAnnInvisAttr = rpa;
                }
                ParameterAnnotationEntry[] parameterAnnotationEntries = rpa.getParameterAnnotationEntries();
                for (int j = 0; j < parameterAnnotationEntries.length; j++) {
                    ParameterAnnotationEntry immutableArray = rpa.getParameterAnnotationEntries()[j];
                    List<AnnotationEntryGen> mutable = makeMutableVersion(immutableArray.getAnnotationEntries());
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
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public List<AnnotationEntryGen> getAnnotationsOnParameter(int i) {
        ensureExistingParameterAnnotationsUnpacked();
        if (!hasParameterAnnotations || i > argTypes.length) {
            return null;
        }
        return paramAnnotations[i];
    }

    public String getArgumentName(int i) {
        return argNames[i];
    }

    public String[] getArgumentNames() {
        return argNames.clone();
    }

    public Type getArgumentType(int i) {
        return argTypes[i];
    }

    public Type[] getArgumentTypes() {
        return argTypes.clone();
    }

    public ClassFileName getClassName() {
        return className;
    }

    public Attribute[] getCodeAttributes() {
        Attribute[] attributes = new Attribute[codeAttrsList.size()];
        codeAttrsList.toArray(attributes);
        return attributes;
    }

    private CodeException[] getCodeExceptions() {
        int size = exceptionList.size();
        CodeException[] c_exc = new CodeException[size];
        for (int i = 0; i < size; i++) {
            CodeExceptionGen c = exceptionList.get(i);
            c_exc[i] = c.getCodeException(super.getConstantPool());
        }
        return c_exc;
    }

    public CodeExceptionGen[] getExceptionHandlers() {
        CodeExceptionGen[] cg = new CodeExceptionGen[exceptionList.size()];
        exceptionList.toArray(cg);
        return cg;
    }

    public String[] getExceptions() {
        String[] e = new String[throwsList.size()];
        throwsList.toArray(e);
        return e;
    }

    private ExceptionTable getExceptionTable(ConstantPoolGen cp) {
        int size = throwsList.size();
        int[] ex = new int[size];
        for (int i = 0; i < size; i++) {
            ex[i] = cp.addClass(throwsList.get(i));
        }
        return new ExceptionTable(cp.addUtf8("Exceptions"), 2 + 2 * size, ex, cp.getConstantPool());
    }

    public InstructionList getInstructionList() {
        return il;
    }

    public LineNumberGen[] getLineNumbers() {
        LineNumberGen[] lg = new LineNumberGen[lineNumberList.size()];
        lineNumberList.toArray(lg);
        return lg;
    }

    public LineNumberTable getLineNumberTable(ConstantPoolGen cp) {
        int size = lineNumberList.size();
        LineNumber[] ln = new LineNumber[size];
        for (int i = 0; i < size; i++) {
            ln[i] = lineNumberList.get(i).getLineNumber();
        }
        return new LineNumberTable(cp.addUtf8("LineNumberTable"), 2 + ln.length * 4, ln, cp.getConstantPool());
    }

    public LocalVariableGen[] getLocalVariables() {
        int size = variableList.size();
        LocalVariableGen[] lg = new LocalVariableGen[size];
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

    public LocalVariableTable getLocalVariableTable(ConstantPoolGen cp) {
        LocalVariableGen[] lg = getLocalVariables();
        int size = lg.length;
        LocalVariable[] lv = new LocalVariable[size];
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
        String signature = getSignature();
        ConstantPoolGen _cp = super.getConstantPool();
        int name_index = _cp.addUtf8(super.getName());
        int signature_index = _cp.addUtf8(signature);
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
            if (lvt != null) {
                adjustLocalVariableTypeTable(lvt);
            }
            addCodeAttribute(localVariableTypeTable);
        }
        if ((lineNumberList.size() > 0) && !stripAttributes) {
            addCodeAttribute(lnt = getLineNumberTable(_cp));
        }
        Attribute[] code_attrs = getCodeAttributes();
        int attrs_len = 0;
        for (Attribute code_attr : code_attrs) {
            attrs_len += code_attr.getLength() + 6;
        }
        CodeException[] c_exc = getCodeExceptions();
        int exc_len = c_exc.length * 8;
        Code code = null;
        if ((il != null) && !isAbstract() && !isNative()) {
            Attribute[] attributes = getAttributes();
            for (Attribute a : attributes) {
                if (a instanceof Code) {
                    removeAttribute(a);
                }
            }
            code = new Code(_cp.addUtf8("Code"), 8 + byte_code.length + 2 + exc_len + 2 + attrs_len, maxStack, maxLocals, byte_code, c_exc, code_attrs, _cp.getConstantPool());
            addAttribute(code);
        }
        Attribute[] annotations = addRuntimeAnnotationsAsAttribute(_cp);
        Attribute[] parameterAnnotations = addRuntimeParameterAnnotationsAsAttribute(_cp);
        ExceptionTable et = null;
        if (throwsList.size() > 0) {
            addAttribute(et = getExceptionTable(_cp));
        }
        Method m = new Method(super.getAccessFlags(), name_index, signature_index, getAttributes(), _cp.getConstantPool());
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

    private List<AnnotationEntryGen> makeMutableVersion(AnnotationEntry[] mutableArray) {
        List<AnnotationEntryGen> result = new ArrayList<>();
        for (AnnotationEntry element : mutableArray) {
            result.add(new AnnotationEntryGen(element, getConstantPool(), false));
        }
        return result;
    }

    public void removeCodeAttribute(Attribute a) {
        codeAttrsList.remove(a);
    }

    public void removeCodeAttributes() {
        localVariableTypeTable = null;
        codeAttrsList.clear();
    }

    public void removeException(String c) {
        throwsList.remove(c);
    }

    public void removeExceptionHandler(CodeExceptionGen c) {
        exceptionList.remove(c);
    }

    public void removeExceptionHandlers() {
        exceptionList.clear();
    }

    public void removeExceptions() {
        throwsList.clear();
    }

    public void removeLineNumber(LineNumberGen l) {
        lineNumberList.remove(l);
    }

    public void removeLineNumbers() {
        lineNumberList.clear();
    }

    public void removeLocalVariable(LocalVariableGen l) {
        l.dispose();
        variableList.remove(l);
    }

    public void removeLocalVariables() {
        for (LocalVariableGen lv : variableList) {
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
                    } catch (TargetLostException e) {
                        for (InstructionHandle target : e.getTargets()) {
                            for (InstructionTargeter targeter : target.getTargeters()) {
                                targeter.updateTarget(target, next);
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeObserver(MethodObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

    public void removeRuntimeAttributes(Attribute[] attrs) {
        for (Attribute attr : attrs) {
            removeAttribute(attr);
        }
    }

    public void setArgumentName(int i, String name) {
        argNames[i] = name;
    }

    public void setArgumentNames(String[] arg_names) {
        this.argNames = arg_names;
    }

    public void setArgumentType(int i, Type type) {
        argTypes[i] = type;
    }

    public void setArgumentTypes(Type[] arg_types) {
        this.argTypes = arg_types;
    }

    public void setClassName(ClassFileName class_name) {
        this.className = class_name;
    }

    public void setInstructionList(InstructionList il) {
        this.il = il;
    }

    public void setMaxLocals() throws ClassFormatException, IOException {
        if (il != null) {
            int max = isStatic() ? 0 : 1;
            if (argTypes != null) {
                for (Type arg_type : argTypes) {
                    max += arg_type.getSize();
                }
            }
            for (InstructionHandle ih = il.getStart(); ih != null; ih = ih.getNext()) {
                Instruction ins = ih.getInstruction();
                if ((ins instanceof LocalVariableInstruction) || (ins instanceof RET) || (ins instanceof IINC)) {
                    int index = ((IndexedInstruction) ins).getIndex() + ((TypedInstruction) ins).getType(super.getConstantPool()).getSize();
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

    public void setMaxLocals(int m) {
        maxLocals = m;
    }

    public void setMaxStack() {
        if (il != null) {
            maxStack = getMaxStack(super.getConstantPool(), il, getExceptionHandlers());
        } else {
            maxStack = 0;
        }
    }

    public void setMaxStack(int m) {
        maxStack = m;
    }

    public void setReturnType(Type return_type) {
        setType(return_type);
    }

    public void stripAttributes(boolean flag) {
        stripAttributes = flag;
    }

    @Override
    public String toString() {
        String access = Utility.accessToString(super.getAccessFlags());
        String signature = Type.getMethodSignature(super.getType(), argTypes);
        signature = Utility.methodSignatureToString(signature, super.getName(), access, true, getLocalVariableTable(super.getConstantPool()));
        StringBuilder buf = new StringBuilder(signature);
        for (Attribute a : getAttributes()) {
            if (!((a instanceof Code) || (a instanceof ExceptionTable))) {
                buf.append(" [").append(a).append("]");
            }
        }
        if (throwsList.size() > 0) {
            for (String throwsDescriptor : throwsList) {
                buf.append("\n\t\tthrows ").append(throwsDescriptor);
            }
        }
        return buf.toString();
    }

    public void update() {
        if (observers != null) {
            for (MethodObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    private void updateLocalVariableTable(LocalVariableTable a) {
        LocalVariable[] lv = a.getLocalVariableTable();
        removeLocalVariables();
        for (LocalVariable l : lv) {
            InstructionHandle start = il.findHandle(l.getStartPC());
            InstructionHandle end = il.findHandle(l.getStartPC() + l.getLength());
            if (null == start) {
                start = il.getStart();
            }
            addLocalVariable(l.getName(), Type.getType(l.getSignature()), l.getIndex(), start, end, l.getOrigIndex());
        }
    }

    private static byte[] getByteCodes(Method method) {
        Code code = method.getCode();
        if (code == null) {
            throw new IllegalStateException(String.format("The method '%s' has no code.", method));
        }
        return code.getCode();
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static int getMaxStack(ConstantPoolGen cp, InstructionList il, CodeExceptionGen[] et) {
        BranchStack branchTargets = new BranchStack();
        for (CodeExceptionGen element : et) {
            InstructionHandle handler_pc = element.getHandlerPC();
            if (handler_pc != null) {
                branchTargets.push(handler_pc, 1);
            }
        }
        int stackDepth = 0;
        int maxStackDepth = 0;
        InstructionHandle ih = il.getStart();
        while (ih != null) {
            Instruction instruction = ih.getInstruction();
            InstructionOpCodes opcode = instruction.getOpcode();
            int delta = instruction.produceStack(cp) - instruction.consumeStack(cp);
            stackDepth += delta;
            if (stackDepth > maxStackDepth) {
                maxStackDepth = stackDepth;
            }
            if (instruction instanceof BranchInstruction) {
                BranchInstruction branch = (BranchInstruction) instruction;
                if (instruction instanceof Select) {
                    Select select = (Select) branch;
                    InstructionHandle[] targets = select.getTargets();
                    for (InstructionHandle target : targets) {
                        branchTargets.push(target, stackDepth);
                    }
                    ih = null;
                } else if (!(branch instanceof IfInstruction)) {
                    if (opcode == InstructionOpCodes.JSR || opcode == InstructionOpCodes.JSR_W) {
                        branchTargets.push(ih.getNext(), stackDepth - 1);
                    }
                    ih = null;
                }
                branchTargets.push(branch.getTarget(), stackDepth);
            } else if (opcode == InstructionOpCodes.ATHROW || opcode == InstructionOpCodes.RET || (opcode.getOpcode() >= InstructionOpCodes.IRETURN.getOpcode() && opcode.getOpcode() <= InstructionOpCodes.RETURN.getOpcode())) {
                ih = null;
            }
            if (ih != null) {
                ih = ih.getNext();
            }
            if (ih == null) {
                BranchTarget bt = branchTargets.pop();
                if (bt != null) {
                    ih = bt.target;
                    stackDepth = bt.stackDepth;
                }
            }
        }
        return maxStackDepth;
    }

    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
