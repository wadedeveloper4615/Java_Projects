package com.wade.decompiler.generate.attribute;

import java.io.IOException;

import com.wade.decompiler.classfile.ClassFormatException;
import com.wade.decompiler.classfile.attribute.AnnotationDefault;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.BootstrapMethods;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.attribute.EnclosingMethod;
import com.wade.decompiler.classfile.attribute.ExceptionTable;
import com.wade.decompiler.classfile.attribute.InnerClasses;
import com.wade.decompiler.classfile.attribute.LineNumberTable;
import com.wade.decompiler.classfile.attribute.LocalVariableTable;
import com.wade.decompiler.classfile.attribute.LocalVariableTypeTable;
import com.wade.decompiler.classfile.attribute.MethodParameters;
import com.wade.decompiler.classfile.attribute.Module;
import com.wade.decompiler.classfile.attribute.ModuleMainClass;
import com.wade.decompiler.classfile.attribute.ModulePackages;
import com.wade.decompiler.classfile.attribute.NestHost;
import com.wade.decompiler.classfile.attribute.NestMembers;
import com.wade.decompiler.classfile.attribute.PMGClass;
import com.wade.decompiler.classfile.attribute.RuntimeInvisibleParameterAnnotations;
import com.wade.decompiler.classfile.attribute.RuntimeVisibleAnnotations;
import com.wade.decompiler.classfile.attribute.RuntimeVisibleParameterAnnotations;
import com.wade.decompiler.classfile.attribute.Signature;
import com.wade.decompiler.classfile.attribute.SourceFile;
import com.wade.decompiler.classfile.attribute.StackMap;
import com.wade.decompiler.classfile.attribute.Synthetic;
import com.wade.decompiler.classfile.attribute.Unknown;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class AttributeGen {
    private ClassFileAttributes tag;
    private int nameIndex;
    private int length;
    private String name;
    protected ConstantPool constantPool;

    public AttributeGen(Attribute attribute, ConstantPool constantPool) {
        this.tag = attribute.getTag();
        this.nameIndex = attribute.getNameIndex();
        this.length = attribute.getLength();
        this.name = ((ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.constantPool = constantPool;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AttributeGen other = (AttributeGen) obj;
        if (length != other.length)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nameIndex != other.nameIndex)
            return false;
        if (tag != other.tag)
            return false;
        return true;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public ClassFileAttributes getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + length;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + nameIndex;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AttributeGen [tag=" + tag + ", name=" + name + ", length=" + length + ", nameIndex=" + nameIndex + "]";
    }

    public static AttributeGen readAttribute(Attribute attribute, ConstantPool constantPool) throws IOException, ClassFormatException {
        switch (attribute.getTag()) {
            case ATTR_UNKNOWN:
                return new UnknownGen((Unknown) attribute, constantPool);
            case ATTR_CONSTANT_VALUE:
                return new ConstantValueGen((ConstantValue) attribute, constantPool);
            case ATTR_SOURCE_FILE:
                return new SourceFileGen((SourceFile) attribute, constantPool);
            case ATTR_CODE:
                return new CodeGen((Code) attribute, constantPool);
            case ATTR_EXCEPTIONS:
                return new ExceptionTableGen((ExceptionTable) attribute, constantPool);
            case ATTR_LINE_NUMBER_TABLE:
                return new LineNumberTableGen((LineNumberTable) attribute, constantPool);
            case ATTR_LOCAL_VARIABLE_TABLE:
                return new LocalVariableTableGen((LocalVariableTable) attribute, constantPool);
            case ATTR_INNER_CLASSES:
                return new InnerClassesGen((InnerClasses) attribute, constantPool);
            case ATTR_SYNTHETIC:
                return new SyntheticGen((Synthetic) attribute, constantPool);
            case ATTR_DEPRECATED:
                return new DeprecatedGen((com.wade.decompiler.classfile.attribute.Deprecated) attribute, constantPool);
            case ATTR_PMG:
                return new PMGClassGen((PMGClass) attribute, constantPool);
            case ATTR_SIGNATURE:
                return new SignatureGen((Signature) attribute, constantPool);
            case ATTR_STACK_MAP:
                return new UnknownGen((Unknown) attribute, constantPool);
            case ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
                return new RuntimeVisibleAnnotationsGen((RuntimeVisibleAnnotations) attribute, constantPool);
            case ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
                return new RuntimeInvisibleAnnotationsGen((RuntimeVisibleAnnotations) attribute, constantPool);
            case ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeVisibleParameterAnnotationsGen((RuntimeVisibleParameterAnnotations) attribute, constantPool);
            case ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeInvisibleParameterAnnotationsGen((RuntimeInvisibleParameterAnnotations) attribute, constantPool);
            case ATTR_ANNOTATION_DEFAULT:
                return new AnnotationDefaultGen((AnnotationDefault) attribute, constantPool);
            case ATTR_LOCAL_VARIABLE_TYPE_TABLE:
                return new LocalVariableTypeTableGen((LocalVariableTypeTable) attribute, constantPool);
            case ATTR_ENCLOSING_METHOD:
                return new EnclosingMethodGen((EnclosingMethod) attribute, constantPool);
            case ATTR_STACK_MAP_TABLE:
                return new StackMapGen((StackMap) attribute, constantPool);
            case ATTR_BOOTSTRAP_METHODS:
                return new BootstrapMethodsGen((BootstrapMethods) attribute, constantPool);
            case ATTR_METHOD_PARAMETERS:
                return new MethodParametersGen((MethodParameters) attribute, constantPool);
            case ATTR_MODULE:
                return new ModuleGen((Module) attribute, constantPool);
            case ATTR_MODULE_PACKAGES:
                return new ModulePackagesGen((ModulePackages) attribute, constantPool);
            case ATTR_MODULE_MAIN_CLASS:
                return new ModuleMainClassGen((ModuleMainClass) attribute, constantPool);
            case ATTR_NEST_HOST:
                return new NestHostGen((NestHost) attribute, constantPool);
            case ATTR_NEST_MEMBERS:
                return new NestMembersGen((NestMembers) attribute, constantPool);
            default:
                throw new IllegalStateException("Unrecognized attribute type tag parsed: " + attribute.getTag());
        }
    }
}
