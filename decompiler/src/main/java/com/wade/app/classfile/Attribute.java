package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.classfile.attribute.BootstrapMethods;
import com.wade.app.classfile.attribute.ConstantValue;
import com.wade.app.classfile.attribute.EnclosingMethod;
import com.wade.app.classfile.attribute.ExceptionTable;
import com.wade.app.classfile.attribute.InnerClasses;
import com.wade.app.classfile.attribute.LineNumberTable;
import com.wade.app.classfile.attribute.LocalVariableTable;
import com.wade.app.classfile.attribute.PMGClass;
import com.wade.app.classfile.attribute.RuntimeInvisibleAnnotations;
import com.wade.app.classfile.attribute.RuntimeInvisibleParameterAnnotations;
import com.wade.app.classfile.attribute.RuntimeVisibleAnnotations;
import com.wade.app.classfile.attribute.RuntimeVisibleParameterAnnotations;
import com.wade.app.classfile.attribute.SourceFile;
import com.wade.app.classfile.attribute.StackMap;
import com.wade.app.classfile.attribute.Synthetic;
import com.wade.app.classfile.attribute.Unknown;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.enums.ClassFileAttributes;
import com.wade.app.enums.ClassFileConstants;

public abstract class Attribute {
    private ClassFileAttributes tag;
    private int name_index;
    private int length;
    private ConstantPool constant_pool;

    protected Attribute(ClassFileAttributes tag, int name_index, int length, ConstantPool constant_pool) {
        this.tag = tag;
        this.name_index = name_index;
        this.length = length;
        this.constant_pool = constant_pool;
    }

    public ConstantPool getConstant_pool() {
        return constant_pool;
    }

    public int getLength() {
        return length;
    }

    public int getName_index() {
        return name_index;
    }

    public ClassFileAttributes getTag() {
        return tag;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public static Attribute readAttribute(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        ClassFileAttributes tag = ClassFileAttributes.ATTR_UNKNOWN;
        int name_index = file.readUnsignedShort();
        ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8);
        String name = c.getBytes();
        int length = file.readInt();
        for (ClassFileAttributes cfa : ClassFileAttributes.values()) {
            if (name.equals(cfa.getName())) {
                tag = cfa;
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
                return new com.wade.app.classfile.attribute.Deprecated(name_index, length, file, constant_pool);
            case ATTR_PMG:
                return new PMGClass(name_index, length, file, constant_pool);
            case ATTR_SIGNATURE:
                return new Signature(name_index, length, file, constant_pool);
            case ATTR_STACK_MAP:
                System.out.println("Warning: Obsolete StackMap attribute ignored.");
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
                return new com.wade.app.classfile.attribute.Module(name_index, length, file, constant_pool);
            case ATTR_MODULE_PACKAGES:
                return new com.wade.app.classfile.attribute.ModulePackages(name_index, length, file, constant_pool);
            case ATTR_MODULE_MAIN_CLASS:
                return new com.wade.app.classfile.attribute.ModuleMainClass(name_index, length, file, constant_pool);
            case ATTR_NEST_HOST:
                return new com.wade.app.classfile.attribute.NestHost(name_index, length, file, constant_pool);
            case ATTR_NEST_MEMBERS:
                return new com.wade.app.classfile.attribute.NestMembers(name_index, length, file, constant_pool);
            default:
                throw new IllegalStateException("Unrecognized attribute type tag parsed: " + tag);
        }
    }
}
