package org.apache.bcel.classfile;

import java.util.Stack;

import org.apache.bcel.classfile.annotations.ParameterAnnotationEntry;
import org.apache.bcel.classfile.attribute.AnnotationDefault;
import org.apache.bcel.classfile.attribute.Annotations;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.BootstrapMethods;
import org.apache.bcel.classfile.attribute.Code;
import org.apache.bcel.classfile.attribute.Deprecated;
import org.apache.bcel.classfile.attribute.EnclosingMethod;
import org.apache.bcel.classfile.attribute.ExceptionTable;
import org.apache.bcel.classfile.attribute.InnerClasses;
import org.apache.bcel.classfile.attribute.LineNumberTable;
import org.apache.bcel.classfile.attribute.LocalVariableTable;
import org.apache.bcel.classfile.attribute.LocalVariableTypeTable;
import org.apache.bcel.classfile.attribute.MethodParameters;
import org.apache.bcel.classfile.attribute.Module;
import org.apache.bcel.classfile.attribute.ModuleMainClass;
import org.apache.bcel.classfile.attribute.ModulePackages;
import org.apache.bcel.classfile.attribute.NestHost;
import org.apache.bcel.classfile.attribute.ParameterAnnotations;
import org.apache.bcel.classfile.attribute.Signature;
import org.apache.bcel.classfile.attribute.SourceFile;
import org.apache.bcel.classfile.attribute.StackMap;
import org.apache.bcel.classfile.attribute.Synthetic;
import org.apache.bcel.classfile.constant.Constant;
import org.apache.bcel.classfile.constant.ConstantClass;
import org.apache.bcel.classfile.constant.ConstantDouble;
import org.apache.bcel.classfile.constant.ConstantDynamic;
import org.apache.bcel.classfile.constant.ConstantFieldref;
import org.apache.bcel.classfile.constant.ConstantFloat;
import org.apache.bcel.classfile.constant.ConstantInteger;
import org.apache.bcel.classfile.constant.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.constant.ConstantInvokeDynamic;
import org.apache.bcel.classfile.constant.ConstantLong;
import org.apache.bcel.classfile.constant.ConstantMethodHandle;
import org.apache.bcel.classfile.constant.ConstantMethodType;
import org.apache.bcel.classfile.constant.ConstantMethodref;
import org.apache.bcel.classfile.constant.ConstantModule;
import org.apache.bcel.classfile.constant.ConstantNameAndType;
import org.apache.bcel.classfile.constant.ConstantPackage;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantString;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.classfile.constant.ConstantValue;
import org.apache.bcel.classfile.constant.Unknown;

public class DescendingVisitor implements Visitor {
    private final JavaClass clazz;
    private final Visitor visitor;
    private final Stack<Object> stack = new Stack<>();

    public DescendingVisitor(final JavaClass clazz, final Visitor visitor) {
        this.clazz = clazz;
        this.visitor = visitor;
    }

    public Object current() {
        return stack.peek();
    }

    public Object predecessor() {
        return predecessor(0);
    }

    public Object predecessor(final int level) {
        final int size = stack.size();
        if ((size < 2) || (level < 0)) {
            return null;
        }
        return stack.elementAt(size - (level + 2)); // size - 1 == current
    }

    public void visit() {
        clazz.accept(this);
    }

    @Override
    public void visitAnnotation(final Annotations annotation) {
        stack.push(annotation);
        annotation.accept(visitor);
        final AnnotationEntry[] entries = annotation.getAnnotationEntries();
        for (final AnnotationEntry entrie : entries) {
            entrie.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitAnnotationDefault(final AnnotationDefault obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitAnnotationEntry(final AnnotationEntry annotationEntry) {
        stack.push(annotationEntry);
        annotationEntry.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitBootstrapMethods(final BootstrapMethods bm) {
        stack.push(bm);
        bm.accept(visitor);
        // BootstrapMethod[] bms = bm.getBootstrapMethods();
        // for (int i = 0; i < bms.length; i++)
        // {
        // bms[i].accept(this);
        // }
        stack.pop();
    }

    @Override
    public void visitCode(final Code code) {
        stack.push(code);
        code.accept(visitor);
        final CodeException[] table = code.getExceptionTable();
        for (final CodeException element : table) {
            element.accept(this);
        }
        final Attribute[] attributes = code.getAttributes();
        for (final Attribute attribute : attributes) {
            attribute.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitCodeException(final CodeException ce) {
        stack.push(ce);
        ce.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantClass(final ConstantClass constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantDouble(final ConstantDouble constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantDynamic(final ConstantDynamic obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantFieldref(final ConstantFieldref constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantFloat(final ConstantFloat constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantInteger(final ConstantInteger constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantInterfaceMethodref(final ConstantInterfaceMethodref constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantInvokeDynamic(final ConstantInvokeDynamic constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantLong(final ConstantLong constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantMethodHandle(final ConstantMethodHandle obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantMethodref(final ConstantMethodref constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantMethodType(final ConstantMethodType obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantModule(final ConstantModule obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantNameAndType(final ConstantNameAndType constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantPackage(final ConstantPackage obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantPool(final ConstantPool cp) {
        stack.push(cp);
        cp.accept(visitor);
        final Constant[] constants = cp.getConstantPool();
        for (int i = 1; i < constants.length; i++) {
            if (constants[i] != null) {
                constants[i].accept(this);
            }
        }
        stack.pop();
    }

    @Override
    public void visitConstantString(final ConstantString constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantUtf8(final ConstantUtf8 constant) {
        stack.push(constant);
        constant.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitConstantValue(final ConstantValue cv) {
        stack.push(cv);
        cv.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitDeprecated(final Deprecated attribute) {
        stack.push(attribute);
        attribute.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitEnclosingMethod(final EnclosingMethod obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitExceptionTable(final ExceptionTable table) {
        stack.push(table);
        table.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitField(final Field field) {
        stack.push(field);
        field.accept(visitor);
        final Attribute[] attributes = field.getAttributes();
        for (final Attribute attribute : attributes) {
            attribute.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitInnerClass(final InnerClass inner) {
        stack.push(inner);
        inner.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitInnerClasses(final InnerClasses ic) {
        stack.push(ic);
        ic.accept(visitor);
        final InnerClass[] ics = ic.getInnerClasses();
        for (final InnerClass ic2 : ics) {
            ic2.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitJavaClass(final JavaClass _clazz) {
        stack.push(_clazz);
        _clazz.accept(visitor);
        final Field[] fields = _clazz.getFields();
        for (final Field field : fields) {
            field.accept(this);
        }
        final Method[] methods = _clazz.getMethods();
        for (final Method method : methods) {
            method.accept(this);
        }
        final Attribute[] attributes = _clazz.getAttributes();
        for (final Attribute attribute : attributes) {
            attribute.accept(this);
        }
        _clazz.getConstantPool().accept(this);
        stack.pop();
    }

    @Override
    public void visitLineNumber(final LineNumber number) {
        stack.push(number);
        number.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitLineNumberTable(final LineNumberTable table) {
        stack.push(table);
        table.accept(visitor);
        final LineNumber[] numbers = table.getLineNumberTable();
        for (final LineNumber number : numbers) {
            number.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitLocalVariable(final LocalVariable var) {
        stack.push(var);
        var.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitLocalVariableTable(final LocalVariableTable table) {
        stack.push(table);
        table.accept(visitor);
        final LocalVariable[] vars = table.getLocalVariableTable();
        for (final LocalVariable var : vars) {
            var.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitLocalVariableTypeTable(final LocalVariableTypeTable obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitMethod(final Method method) {
        stack.push(method);
        method.accept(visitor);
        final Attribute[] attributes = method.getAttributes();
        for (final Attribute attribute : attributes) {
            attribute.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitMethodParameter(final MethodParameter obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitMethodParameters(final MethodParameters obj) {
        stack.push(obj);
        obj.accept(visitor);
        final MethodParameter[] table = obj.getParameters();
        for (final MethodParameter element : table) {
            element.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitModule(final Module obj) {
        stack.push(obj);
        obj.accept(visitor);
        final ModuleRequires[] rtable = obj.getRequiresTable();
        for (final ModuleRequires element : rtable) {
            element.accept(this);
        }
        final ModuleExports[] etable = obj.getExportsTable();
        for (final ModuleExports element : etable) {
            element.accept(this);
        }
        final ModuleOpens[] otable = obj.getOpensTable();
        for (final ModuleOpens element : otable) {
            element.accept(this);
        }
        final ModuleProvides[] ptable = obj.getProvidesTable();
        for (final ModuleProvides element : ptable) {
            element.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitModuleExports(final ModuleExports obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitModuleMainClass(final ModuleMainClass obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitModuleOpens(final ModuleOpens obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitModulePackages(final ModulePackages obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitModuleProvides(final ModuleProvides obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitModuleRequires(final ModuleRequires obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitNestHost(final NestHost obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitNestMembers(final NestMembers obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitParameterAnnotation(final ParameterAnnotations obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitParameterAnnotationEntry(final ParameterAnnotationEntry obj) {
        stack.push(obj);
        obj.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitSignature(final Signature attribute) {
        stack.push(attribute);
        attribute.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitSourceFile(final SourceFile attribute) {
        stack.push(attribute);
        attribute.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitStackMap(final StackMap table) {
        stack.push(table);
        table.accept(visitor);
        final StackMapEntry[] vars = table.getStackMap();
        for (final StackMapEntry var : vars) {
            var.accept(this);
        }
        stack.pop();
    }

    @Override
    public void visitStackMapEntry(final StackMapEntry var) {
        stack.push(var);
        var.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitSynthetic(final Synthetic attribute) {
        stack.push(attribute);
        attribute.accept(visitor);
        stack.pop();
    }

    @Override
    public void visitUnknown(final Unknown attribute) {
        stack.push(attribute);
        attribute.accept(visitor);
        stack.pop();
    }
}
