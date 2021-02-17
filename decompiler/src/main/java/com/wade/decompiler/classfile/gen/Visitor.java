package com.wade.decompiler.classfile.gen;

import com.wade.decompiler.classfile.CodeException;
import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.ModuleExports;
import com.wade.decompiler.classfile.ModuleOpens;
import com.wade.decompiler.classfile.ModuleProvides;
import com.wade.decompiler.classfile.ModuleRequires;
import com.wade.decompiler.classfile.attribute.AnnotationDefault;
import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.Annotations;
import com.wade.decompiler.classfile.attribute.BootstrapMethods;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.attribute.Deprecated;
import com.wade.decompiler.classfile.attribute.EnclosingMethod;
import com.wade.decompiler.classfile.attribute.ExceptionTable;
import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.attribute.InnerClasses;
import com.wade.decompiler.classfile.attribute.LineNumber;
import com.wade.decompiler.classfile.attribute.LineNumberTable;
import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.attribute.LocalVariableTable;
import com.wade.decompiler.classfile.attribute.LocalVariableTypeTable;
import com.wade.decompiler.classfile.attribute.MethodParameter;
import com.wade.decompiler.classfile.attribute.MethodParameters;
import com.wade.decompiler.classfile.attribute.Module;
import com.wade.decompiler.classfile.attribute.ModuleMainClass;
import com.wade.decompiler.classfile.attribute.ModulePackages;
import com.wade.decompiler.classfile.attribute.NestHost;
import com.wade.decompiler.classfile.attribute.NestMembers;
import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.attribute.ParameterAnnotations;
import com.wade.decompiler.classfile.attribute.Signature;
import com.wade.decompiler.classfile.attribute.SourceFile;
import com.wade.decompiler.classfile.attribute.StackMap;
import com.wade.decompiler.classfile.attribute.StackMapEntry;
import com.wade.decompiler.classfile.attribute.Synthetic;
import com.wade.decompiler.classfile.attribute.Unknown;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantDouble;
import com.wade.decompiler.classfile.constant.ConstantDynamic;
import com.wade.decompiler.classfile.constant.ConstantFieldref;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantInteger;
import com.wade.decompiler.classfile.constant.ConstantInterfaceMethodref;
import com.wade.decompiler.classfile.constant.ConstantInvokeDynamic;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantMethodHandle;
import com.wade.decompiler.classfile.constant.ConstantMethodType;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantModule;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPackage;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantString;
import com.wade.decompiler.classfile.constant.ConstantUtf8;

public interface Visitor {
    void visitAnnotation(Annotations obj);

    void visitAnnotationDefault(AnnotationDefault obj);

    void visitAnnotationEntry(AnnotationEntry obj);

    void visitBootstrapMethods(BootstrapMethods obj);

    void visitCode(Code obj);

    void visitCodeException(CodeException obj);

    void visitConstantClass(ConstantClass obj);

    void visitConstantDouble(ConstantDouble obj);

    default void visitConstantDynamic(ConstantDynamic constantDynamic) {
        // empty
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

    default void visitMethodParameter(MethodParameter obj) {
        // empty
    }

    void visitMethodParameters(MethodParameters obj);

    default void visitModule(Module constantModule) {
        // empty
    }

    default void visitModuleExports(ModuleExports constantModule) {
        // empty
    }

    default void visitModuleMainClass(ModuleMainClass obj) {
        // empty
    }

    default void visitModuleOpens(ModuleOpens constantModule) {
        // empty
    }

    default void visitModulePackages(ModulePackages constantModule) {
        // empty
    }

    default void visitModuleProvides(ModuleProvides constantModule) {
        // empty
    }

    default void visitModuleRequires(ModuleRequires constantModule) {
        // empty
    }

    default void visitNestHost(NestHost obj) {
        // empty
    }

    default void visitNestMembers(NestMembers obj) {
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
