package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
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

    public static Attribute readAttribute(DataInput file, ConstantPool constantPool) throws IOException, ClassFormatException {
        ClassFileAttributes tag = ClassFileAttributes.ATTR_UNKNOWN;
        int nameIndex = file.readUnsignedShort();
        String name = ((ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        int length = file.readInt();

        for (ClassFileAttributes currentTag : ClassFileAttributes.values()) {
            if (name.equals(currentTag.getName())) {
                tag = currentTag;
                break;
            }
        }
        switch (tag) {
            case ATTR_UNKNOWN:
                return new Unknown(nameIndex, length, file, constantPool);
            case ATTR_CONSTANT_VALUE:
                return new ConstantValue(nameIndex, length, file, constantPool);
            case ATTR_SOURCE_FILE:
                return new SourceFile(nameIndex, length, file, constantPool);
            case ATTR_CODE:
                return new Code(nameIndex, length, file, constantPool);
            case ATTR_EXCEPTIONS:
                return new ExceptionTable(nameIndex, length, file, constantPool);
            case ATTR_LINE_NUMBER_TABLE:
                return new LineNumberTable(nameIndex, length, file, constantPool);
            case ATTR_LOCAL_VARIABLE_TABLE:
                return new LocalVariableTable(nameIndex, length, file, constantPool);
            case ATTR_INNER_CLASSES:
                return new InnerClasses(nameIndex, length, file, constantPool);
            case ATTR_SYNTHETIC:
                return new Synthetic(nameIndex, length, file, constantPool);
            case ATTR_DEPRECATED:
                return new Deprecated(nameIndex, length, file, constantPool);
            case ATTR_PMG:
                return new PMGClass(nameIndex, length, file, constantPool);
            case ATTR_SIGNATURE:
                return new Signature(nameIndex, length, file, constantPool);
            case ATTR_STACK_MAP:
                return new Unknown(nameIndex, length, file, constantPool);
            case ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
                return new RuntimeVisibleAnnotations(nameIndex, length, file, constantPool);
            case ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
                return new RuntimeInvisibleAnnotations(nameIndex, length, file, constantPool);
            case ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeVisibleParameterAnnotations(nameIndex, length, file, constantPool);
            case ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new RuntimeInvisibleParameterAnnotations(nameIndex, length, file, constantPool);
            case ATTR_ANNOTATION_DEFAULT:
                return new AnnotationDefault(nameIndex, length, file, constantPool);
            case ATTR_LOCAL_VARIABLE_TYPE_TABLE:
                return new LocalVariableTypeTable(nameIndex, length, file, constantPool);
            case ATTR_ENCLOSING_METHOD:
                return new EnclosingMethod(nameIndex, length, file, constantPool);
            case ATTR_STACK_MAP_TABLE:
                return new StackMap(nameIndex, length, file, constantPool);
            case ATTR_BOOTSTRAP_METHODS:
                return new BootstrapMethods(nameIndex, length, file, constantPool);
            case ATTR_METHOD_PARAMETERS:
                return new MethodParameters(nameIndex, length, file, constantPool);
            case ATTR_MODULE:
                return new Module(nameIndex, length, file, constantPool);
            case ATTR_MODULE_PACKAGES:
                return new ModulePackages(nameIndex, length, file, constantPool);
            case ATTR_MODULE_MAIN_CLASS:
                return new ModuleMainClass(nameIndex, length, file, constantPool);
            case ATTR_NEST_HOST:
                return new NestHost(nameIndex, length, file, constantPool);
            case ATTR_NEST_MEMBERS:
                return new NestMembers(nameIndex, length, file, constantPool);
            default:
                throw new IllegalStateException("Unrecognized attribute type tag parsed: " + tag);
        }
    }
}
