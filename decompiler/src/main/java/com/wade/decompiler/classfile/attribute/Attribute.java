package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.ClassFormatException;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public abstract class Attribute {
    protected int nameIndex;
    protected int length;
    protected ClassFileAttributes tag;
    protected ConstantPool constantPool;

    protected Attribute(ClassFileAttributes tag, int nameIndex, int length, ConstantPool constantPool) {
        this.tag = tag;
        this.nameIndex = nameIndex;
        this.length = length;
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
        Attribute other = (Attribute) obj;
        if (length != other.length)
            return false;
        if (nameIndex != other.nameIndex)
            return false;
        if (tag != other.tag)
            return false;
        return true;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getLength() {
        return length;
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
        result = prime * result + nameIndex;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setTag(ClassFileAttributes tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag.getName();
    }

    public static Attribute readAttribute(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        ClassFileAttributes tag = ClassFileAttributes.ATTR_UNKNOWN;
        int name_index = file.readUnsignedShort();
        String name = ((ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        int length = file.readInt();

        for (ClassFileAttributes currentTag : ClassFileAttributes.values()) {
            if (name.equals(currentTag.getName())) {
                tag = currentTag;
                break;
            }
        }
        switch (tag) {
            case ATTR_UNKNOWN:
                return new Unknown(name_index, length, file, constant_pool);
            case ATTR_CONSTANT_VALUE:
                return new ConstantValue(name_index, length, file, constant_pool);
            case ATTR_SOURCE_FILE:
                return new SourceFile(name_index, length, file, constant_pool);
            case ATTR_CODE:
                return new Code(name_index, length, file, constant_pool);
            case ATTR_EXCEPTIONS:
                return new ExceptionTable(name_index, length, file, constant_pool);
            case ATTR_LINE_NUMBER_TABLE:
                return new LineNumberTable(name_index, length, file, constant_pool);
            case ATTR_LOCAL_VARIABLE_TABLE:
                return new LocalVariableTable(name_index, length, file, constant_pool);
            case ATTR_INNER_CLASSES:
                return new InnerClasses(name_index, length, file, constant_pool);
            case ATTR_SYNTHETIC:
                return new Synthetic(name_index, length, file, constant_pool);
            case ATTR_DEPRECATED:
                return new Deprecated(name_index, length, file, constant_pool);
            case ATTR_PMG:
                return new PMGClass(name_index, length, file, constant_pool);
            case ATTR_SIGNATURE:
                return new Signature(name_index, length, file, constant_pool);
            case ATTR_STACK_MAP:
                return new Unknown(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
                return new RuntimeVisibleAnnotations(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
                return new RuntimeInvisibleAnnotations(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeVisibleParameterAnnotations(name_index, length, file, constant_pool);
            case ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeInvisibleParameterAnnotations(name_index, length, file, constant_pool);
            case ATTR_ANNOTATION_DEFAULT:
                return new AnnotationDefault(name_index, length, file, constant_pool);
            case ATTR_LOCAL_VARIABLE_TYPE_TABLE:
                return new LocalVariableTypeTable(name_index, length, file, constant_pool);
            case ATTR_ENCLOSING_METHOD:
                return new EnclosingMethod(name_index, length, file, constant_pool);
            case ATTR_STACK_MAP_TABLE:
                return new StackMap(name_index, length, file, constant_pool);
            case ATTR_BOOTSTRAP_METHODS:
                return new BootstrapMethods(name_index, length, file, constant_pool);
            case ATTR_METHOD_PARAMETERS:
                return new MethodParameters(name_index, length, file, constant_pool);
            case ATTR_MODULE:
                return new Module(name_index, length, file, constant_pool);
            case ATTR_MODULE_PACKAGES:
                return new ModulePackages(name_index, length, file, constant_pool);
            case ATTR_MODULE_MAIN_CLASS:
                return new ModuleMainClass(name_index, length, file, constant_pool);
            case ATTR_NEST_HOST:
                return new NestHost(name_index, length, file, constant_pool);
            case ATTR_NEST_MEMBERS:
                return new NestMembers(name_index, length, file, constant_pool);
            default:
                throw new IllegalStateException("Unrecognized attribute type tag parsed: " + tag);
        }
    }
}
