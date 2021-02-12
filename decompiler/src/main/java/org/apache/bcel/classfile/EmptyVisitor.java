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

public class EmptyVisitor implements Visitor {
    protected EmptyVisitor() {
    }

    @Override
    public void visitAnnotation(final Annotations obj) {
    }

    @Override
    public void visitAnnotationDefault(final AnnotationDefault obj) {
    }

    @Override
    public void visitAnnotationEntry(final AnnotationEntry obj) {
    }

    @Override
    public void visitBootstrapMethods(final BootstrapMethods obj) {
    }

    @Override
    public void visitCode(final Code obj) {
    }

    @Override
    public void visitCodeException(final CodeException obj) {
    }

    @Override
    public void visitConstantClass(final ConstantClass obj) {
    }

    @Override
    public void visitConstantDouble(final ConstantDouble obj) {
    }

    @Override
    public void visitConstantDynamic(final ConstantDynamic obj) {
    }

    @Override
    public void visitConstantFieldref(final ConstantFieldref obj) {
    }

    @Override
    public void visitConstantFloat(final ConstantFloat obj) {
    }

    @Override
    public void visitConstantInteger(final ConstantInteger obj) {
    }

    @Override
    public void visitConstantInterfaceMethodref(final ConstantInterfaceMethodref obj) {
    }

    @Override
    public void visitConstantInvokeDynamic(final ConstantInvokeDynamic obj) {
    }

    @Override
    public void visitConstantLong(final ConstantLong obj) {
    }

    @Override
    public void visitConstantMethodHandle(final ConstantMethodHandle constantMethodHandle) {
    }

    @Override
    public void visitConstantMethodref(final ConstantMethodref obj) {
    }

    @Override
    public void visitConstantMethodType(final ConstantMethodType obj) {
    }

    @Override
    public void visitConstantModule(final ConstantModule constantModule) {
    }

    @Override
    public void visitConstantNameAndType(final ConstantNameAndType obj) {
    }

    @Override
    public void visitConstantPackage(final ConstantPackage constantPackage) {
    }

    @Override
    public void visitConstantPool(final ConstantPool obj) {
    }

    @Override
    public void visitConstantString(final ConstantString obj) {
    }

    @Override
    public void visitConstantUtf8(final ConstantUtf8 obj) {
    }

    @Override
    public void visitConstantValue(final ConstantValue obj) {
    }

    @Override
    public void visitDeprecated(final Deprecated obj) {
    }

    @Override
    public void visitEnclosingMethod(final EnclosingMethod obj) {
    }

    @Override
    public void visitExceptionTable(final ExceptionTable obj) {
    }

    @Override
    public void visitField(final Field obj) {
    }

    @Override
    public void visitInnerClass(final InnerClass obj) {
    }

    @Override
    public void visitInnerClasses(final InnerClasses obj) {
    }

    @Override
    public void visitJavaClass(final JavaClass obj) {
    }

    @Override
    public void visitLineNumber(final LineNumber obj) {
    }

    @Override
    public void visitLineNumberTable(final LineNumberTable obj) {
    }

    @Override
    public void visitLocalVariable(final LocalVariable obj) {
    }

    @Override
    public void visitLocalVariableTable(final LocalVariableTable obj) {
    }

    @Override
    public void visitLocalVariableTypeTable(final LocalVariableTypeTable obj) {
    }

    @Override
    public void visitMethod(final Method obj) {
    }

    @Override
    public void visitMethodParameter(final MethodParameter obj) {
    }

    @Override
    public void visitMethodParameters(final MethodParameters obj) {
    }

    @Override
    public void visitModule(final Module obj) {
    }

    @Override
    public void visitModuleExports(final ModuleExports obj) {
    }

    @Override
    public void visitModuleMainClass(final ModuleMainClass obj) {
    }

    @Override
    public void visitModuleOpens(final ModuleOpens obj) {
    }

    @Override
    public void visitModulePackages(final ModulePackages obj) {
    }

    @Override
    public void visitModuleProvides(final ModuleProvides obj) {
    }

    @Override
    public void visitModuleRequires(final ModuleRequires obj) {
    }

    @Override
    public void visitNestHost(final NestHost obj) {
    }

    @Override
    public void visitNestMembers(final NestMembers obj) {
    }

    @Override
    public void visitParameterAnnotation(final ParameterAnnotations obj) {
    }

    @Override
    public void visitParameterAnnotationEntry(final ParameterAnnotationEntry parameterAnnotationEntry) {
    }

    @Override
    public void visitSignature(final Signature obj) {
    }

    @Override
    public void visitSourceFile(final SourceFile obj) {
    }

    @Override
    public void visitStackMap(final StackMap obj) {
    }

    @Override
    public void visitStackMapEntry(final StackMapEntry obj) {
    }

    @Override
    public void visitSynthetic(final Synthetic obj) {
    }

    @Override
    public void visitUnknown(final Unknown obj) {
    }
}
