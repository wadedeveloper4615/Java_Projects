package org.apache.bcel.classfile;

import org.apache.bcel.classfile.annotations.ParameterAnnotationEntry;
import org.apache.bcel.classfile.attribute.AnnotationDefault;
import org.apache.bcel.classfile.attribute.Annotations;
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
