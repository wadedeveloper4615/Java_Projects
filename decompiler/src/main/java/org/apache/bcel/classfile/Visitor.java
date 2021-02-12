
package org.apache.bcel.classfile;

public interface Visitor {
    void visitAnnotation(Annotations obj);

    void visitAnnotationDefault(AnnotationDefault obj);

    void visitAnnotationEntry(AnnotationEntry obj);

    void visitBootstrapMethods(BootstrapMethods obj);

    void visitCode(Code obj);

    void visitCodeException(CodeException obj);

    void visitConstantClass(ConstantClass obj);

    void visitConstantDouble(ConstantDouble obj);

    default void visitConstantDynamic(final ConstantDynamic constantDynamic) {
    }

    void visitConstantFieldref(ConstantFieldref obj);

    void visitConstantFloat(ConstantFloat obj);

    void visitConstantInteger(ConstantInteger obj);

    void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj);

    void visitConstantInvokeDynamic(ConstantInvokeDynamic obj);

    void visitConstantLong(ConstantLong obj);

    void visitConstantMethodHandle(ConstantMethodHandle obj);

    void visitConstantMethodref(ConstantMethodref obj);

    void visitConstantMethodType(ConstantMethodType obj);

    void visitConstantModule(ConstantModule constantModule);

    void visitConstantNameAndType(ConstantNameAndType obj);

    void visitConstantPackage(ConstantPackage constantPackage);

    void visitConstantPool(ConstantPool obj);

    void visitConstantString(ConstantString obj);

    void visitConstantUtf8(ConstantUtf8 obj);

    void visitConstantValue(ConstantValue obj);

    void visitDeprecated(Deprecated obj);

    void visitEnclosingMethod(EnclosingMethod obj);

    void visitExceptionTable(ExceptionTable obj);

    void visitField(Field obj);

    void visitInnerClass(InnerClass obj);

    void visitInnerClasses(InnerClasses obj);

    void visitJavaClass(JavaClass obj);

    void visitLineNumber(LineNumber obj);

    void visitLineNumberTable(LineNumberTable obj);

    void visitLocalVariable(LocalVariable obj);

    void visitLocalVariableTable(LocalVariableTable obj);

    void visitLocalVariableTypeTable(LocalVariableTypeTable obj);

    void visitMethod(Method obj);

    default void visitMethodParameter(final MethodParameter obj) {
        // empty
    }

    void visitMethodParameters(MethodParameters obj);

    default void visitModule(final Module constantModule) {
        // empty
    }

    default void visitModuleExports(final ModuleExports constantModule) {
        // empty
    }

    default void visitModuleMainClass(final ModuleMainClass obj) {
        // empty
    }

    default void visitModuleOpens(final ModuleOpens constantModule) {
        // empty
    }

    default void visitModulePackages(final ModulePackages constantModule) {
        // empty
    }

    default void visitModuleProvides(final ModuleProvides constantModule) {
        // empty
    }

    default void visitModuleRequires(final ModuleRequires constantModule) {
        // empty
    }

    default void visitNestHost(final NestHost obj) {
        // empty
    }

    default void visitNestMembers(final NestMembers obj) {
        // empty
    }

    void visitParameterAnnotation(ParameterAnnotations obj);

    void visitParameterAnnotationEntry(ParameterAnnotationEntry obj);

    void visitSignature(Signature obj);

    void visitSourceFile(SourceFile obj);

    void visitStackMap(StackMap obj);

    void visitStackMapEntry(StackMapEntry obj);

    void visitSynthetic(Synthetic obj);

    void visitUnknown(Unknown obj);
}
