package com.wade.decompiler.classfile.gen;

public class DescendingVisitor implements Visitor {
//    private JavaClass clazz;
//    private Visitor visitor;
//    private Stack<Object> stack = new Stack<>();
//
//    public DescendingVisitor(JavaClass clazz, Visitor visitor) {
//        this.clazz = clazz;
//        this.visitor = visitor;
//    }
//
//    public Object current() {
//        return stack.peek();
//    }
//
//    public Object predecessor() {
//        return predecessor(0);
//    }
//
//    public Object predecessor(int level) {
//        int size = stack.size();
//        if ((size < 2) || (level < 0)) {
//            return null;
//        }
//        return stack.elementAt(size - (level + 2)); // size - 1 == current
//    }
//
//    public void visit() {
//        clazz.accept(this);
//    }
//
//    @Override
//    public void visitAnnotation(Annotations annotation) {
//        stack.push(annotation);
//        annotation.accept(visitor);
//        AnnotationEntry[] entries = annotation.getAnnotationEntries();
//        for (AnnotationEntry entrie : entries) {
//            entrie.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitAnnotationDefault(AnnotationDefault obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitAnnotationEntry(AnnotationEntry annotationEntry) {
//        stack.push(annotationEntry);
//        annotationEntry.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitBootstrapMethods(BootstrapMethods bm) {
//        stack.push(bm);
//        bm.accept(visitor);
//        // BootstrapMethod[] bms = bm.getBootstrapMethods();
//        // for (int i = 0; i < bms.length; i++)
//        // {
//        // bms[i].accept(this);
//        // }
//        stack.pop();
//    }
//
//    @Override
//    public void visitCodeException(CodeException ce) {
//        stack.push(ce);
//        ce.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantClass(ConstantClass constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantDouble(ConstantDouble constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantDynamic(ConstantDynamic obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantFieldref(ConstantFieldref constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantFloat(ConstantFloat constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantInteger(ConstantInteger constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantInvokeDynamic(ConstantInvokeDynamic constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantLong(ConstantLong constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantMethodHandle(ConstantMethodHandle obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantMethodref(ConstantMethodref constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantMethodType(ConstantMethodType obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantModule(ConstantModule obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantNameAndType(ConstantNameAndType constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantPackage(ConstantPackage obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantPool(ConstantPool cp) {
//        stack.push(cp);
//        cp.accept(visitor);
//        Constant[] constants = cp.getConstantPool();
//        for (int i = 1; i < constants.length; i++) {
//            if (constants[i] != null) {
//                constants[i].accept(this);
//            }
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantString(ConstantString constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantUtf8(ConstantUtf8 constant) {
//        stack.push(constant);
//        constant.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitConstantValue(ConstantValue cv) {
//        stack.push(cv);
//        cv.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitDeprecated(Deprecated attribute) {
//        stack.push(attribute);
//        attribute.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitEnclosingMethod(EnclosingMethod obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitExceptionTable(ExceptionTable table) {
//        stack.push(table);
//        table.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitField(Field field) {
//        stack.push(field);
//        field.accept(visitor);
//        Attribute[] attributes = field.getAttributes();
//        for (Attribute attribute : attributes) {
//            attribute.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitInnerClass(InnerClass inner) {
//        stack.push(inner);
//        inner.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitInnerClasses(InnerClasses ic) {
//        stack.push(ic);
//        ic.accept(visitor);
//        InnerClass[] ics = ic.getInnerClasses();
//        for (InnerClass ic2 : ics) {
//            ic2.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitJavaClass(JavaClass _clazz) {
//        stack.push(_clazz);
//        _clazz.accept(visitor);
//        Field[] fields = _clazz.getFields();
//        for (Field field : fields) {
//            field.accept(this);
//        }
//        Method[] methods = _clazz.getMethods();
//        for (Method method : methods) {
//            method.accept(this);
//        }
//        Attribute[] attributes = _clazz.getAttributes();
//        for (Attribute attribute : attributes) {
//            attribute.accept(this);
//        }
//        _clazz.getConstantPool().accept(this);
//        stack.pop();
//    }
//
//    @Override
//    public void visitLineNumber(LineNumber number) {
//        stack.push(number);
//        number.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitLineNumberTable(LineNumberTable table) {
//        stack.push(table);
//        table.accept(visitor);
//        LineNumber[] numbers = table.getLineNumberTable();
//        for (LineNumber number : numbers) {
//            number.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitLocalVariable(LocalVariable var) {
//        stack.push(var);
//        var.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitLocalVariableTable(LocalVariableTable table) {
//        stack.push(table);
//        table.accept(visitor);
//        LocalVariable[] vars = table.getLocalVariableTable();
//        for (LocalVariable var : vars) {
//            var.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitLocalVariableTypeTable(LocalVariableTypeTable obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitMethod(Method method) {
//        stack.push(method);
//        method.accept(visitor);
//        Attribute[] attributes = method.getAttributes();
//        for (Attribute attribute : attributes) {
//            attribute.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitMethodParameter(MethodParameter obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitMethodParameters(MethodParameters obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        MethodParameter[] table = obj.getParameters();
//        for (MethodParameter element : table) {
//            element.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitModule(Module obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        ModuleRequires[] rtable = obj.getRequiresTable();
//        for (ModuleRequires element : rtable) {
//            element.accept(this);
//        }
//        ModuleExports[] etable = obj.getExportsTable();
//        for (ModuleExports element : etable) {
//            element.accept(this);
//        }
//        ModuleOpens[] otable = obj.getOpensTable();
//        for (ModuleOpens element : otable) {
//            element.accept(this);
//        }
//        ModuleProvides[] ptable = obj.getProvidesTable();
//        for (ModuleProvides element : ptable) {
//            element.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitModuleExports(ModuleExports obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitModuleMainClass(ModuleMainClass obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitModuleOpens(ModuleOpens obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitModulePackages(ModulePackages obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitModuleProvides(ModuleProvides obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitModuleRequires(ModuleRequires obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitNestHost(NestHost obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitNestMembers(NestMembers obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitParameterAnnotation(ParameterAnnotations obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitParameterAnnotationEntry(ParameterAnnotationEntry obj) {
//        stack.push(obj);
//        obj.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitSignature(Signature attribute) {
//        stack.push(attribute);
//        attribute.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitSourceFile(SourceFile attribute) {
//        stack.push(attribute);
//        attribute.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitStackMap(StackMap table) {
//        stack.push(table);
//        table.accept(visitor);
//        StackMapEntry[] vars = table.getStackMap();
//        for (StackMapEntry var : vars) {
//            var.accept(this);
//        }
//        stack.pop();
//    }
//
//    @Override
//    public void visitStackMapEntry(StackMapEntry var) {
//        stack.push(var);
//        var.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitSynthetic(Synthetic attribute) {
//        stack.push(attribute);
//        attribute.accept(visitor);
//        stack.pop();
//    }
//
//    @Override
//    public void visitUnknown(Unknown attribute) {
//        stack.push(attribute);
//        attribute.accept(visitor);
//        stack.pop();
//    }
}
