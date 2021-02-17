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

public class EmptyVisitor implements Visitor {
    protected EmptyVisitor() {
    }

    @Override
    public void visitAnnotation(Annotations obj) {
    }

    @Override
    public void visitAnnotationDefault(AnnotationDefault obj) {
    }

    @Override
    public void visitAnnotationEntry(AnnotationEntry obj) {
    }

    @Override
    public void visitBootstrapMethods(BootstrapMethods obj) {
    }

    @Override
    public void visitCode(Code obj) {
    }

    @Override
    public void visitCodeException(CodeException obj) {
    }

    @Override
    public void visitConstantClass(ConstantClass obj) {
    }

    @Override
    public void visitConstantDouble(ConstantDouble obj) {
    }

    @Override
    public void visitConstantDynamic(ConstantDynamic obj) {
    }

    @Override
    public void visitConstantFieldref(ConstantFieldref obj) {
    }

    @Override
    public void visitConstantFloat(ConstantFloat obj) {
    }

    @Override
    public void visitConstantInteger(ConstantInteger obj) {
    }

    @Override
    public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj) {
    }

    @Override
    public void visitConstantInvokeDynamic(ConstantInvokeDynamic obj) {
    }

    @Override
    public void visitConstantLong(ConstantLong obj) {
    }

    @Override
    public void visitConstantMethodHandle(ConstantMethodHandle constantMethodHandle) {
    }

    @Override
    public void visitConstantMethodref(ConstantMethodref obj) {
    }

    @Override
    public void visitConstantMethodType(ConstantMethodType obj) {
    }

    @Override
    public void visitConstantModule(ConstantModule constantModule) {
    }

    @Override
    public void visitConstantNameAndType(ConstantNameAndType obj) {
    }

    @Override
    public void visitConstantPackage(ConstantPackage constantPackage) {
    }

    @Override
    public void visitConstantPool(ConstantPool obj) {
    }

    @Override
    public void visitConstantString(ConstantString obj) {
    }

    @Override
    public void visitConstantUtf8(ConstantUtf8 obj) {
    }

    @Override
    public void visitConstantValue(ConstantValue obj) {
    }

    @Override
    public void visitDeprecated(Deprecated obj) {
    }

    @Override
    public void visitEnclosingMethod(EnclosingMethod obj) {
    }

    @Override
    public void visitExceptionTable(ExceptionTable obj) {
    }

    @Override
    public void visitField(Field obj) {
    }

    @Override
    public void visitInnerClass(InnerClass obj) {
    }

    @Override
    public void visitInnerClasses(InnerClasses obj) {
    }

    @Override
    public void visitJavaClass(JavaClass obj) {
    }

    @Override
    public void visitLineNumber(LineNumber obj) {
    }

    @Override
    public void visitLineNumberTable(LineNumberTable obj) {
    }

    @Override
    public void visitLocalVariable(LocalVariable obj) {
    }

    @Override
    public void visitLocalVariableTable(LocalVariableTable obj) {
    }

    @Override
    public void visitLocalVariableTypeTable(LocalVariableTypeTable obj) {
    }

    @Override
    public void visitMethod(Method obj) {
    }

    @Override
    public void visitMethodParameter(MethodParameter obj) {
    }

    @Override
    public void visitMethodParameters(MethodParameters obj) {
    }

    @Override
    public void visitModule(Module obj) {
    }

    @Override
    public void visitModuleExports(ModuleExports obj) {
    }

    @Override
    public void visitModuleMainClass(ModuleMainClass obj) {
    }

    @Override
    public void visitModuleOpens(ModuleOpens obj) {
    }

    @Override
    public void visitModulePackages(ModulePackages obj) {
    }

    @Override
    public void visitModuleProvides(ModuleProvides obj) {
    }

    @Override
    public void visitModuleRequires(ModuleRequires obj) {
    }

    @Override
    public void visitNestHost(NestHost obj) {
    }

    @Override
    public void visitNestMembers(NestMembers obj) {
    }

    @Override
    public void visitParameterAnnotation(ParameterAnnotations obj) {
    }

    @Override
    public void visitParameterAnnotationEntry(ParameterAnnotationEntry parameterAnnotationEntry) {
    }

    @Override
    public void visitSignature(Signature obj) {
    }

    @Override
    public void visitSourceFile(SourceFile obj) {
    }

    @Override
    public void visitStackMap(StackMap obj) {
    }

    @Override
    public void visitStackMapEntry(StackMapEntry obj) {
    }

    @Override
    public void visitSynthetic(Synthetic obj) {
    }

    @Override
    public void visitUnknown(Unknown obj) {
    }
}
