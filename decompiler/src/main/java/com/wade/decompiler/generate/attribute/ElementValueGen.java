package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.AnnotationElementValue;
import com.wade.decompiler.classfile.element.ArrayElementValue;
import com.wade.decompiler.classfile.element.ClassElementValue;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.classfile.element.EnumElementValue;
import com.wade.decompiler.classfile.element.SimpleElementValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ElementValueGen {
    public static final byte STRING = 's';
    public static final byte ENUM_CONSTANT = 'e';
    public static final byte CLASS = 'c';
    public static final byte ANNOTATION = '@';
    public static final byte ARRAY = '[';
    public static final byte PRIMITIVE_INT = 'I';
    public static final byte PRIMITIVE_BYTE = 'B';
    public static final byte PRIMITIVE_CHAR = 'C';
    public static final byte PRIMITIVE_DOUBLE = 'D';
    public static final byte PRIMITIVE_FLOAT = 'F';
    public static final byte PRIMITIVE_LONG = 'J';
    public static final byte PRIMITIVE_SHORT = 'S';
    public static final byte PRIMITIVE_BOOLEAN = 'Z';
    protected int type;

    protected ElementValueGen(int type) {
        this.type = type;
    }

    public static ElementValueGen readElementValue(ElementValue value, ConstantPool constantPool) {
        switch (value.getType()) {
            case 16:
            case PRIMITIVE_CHAR:
            case PRIMITIVE_DOUBLE:
            case PRIMITIVE_FLOAT:
            case PRIMITIVE_INT:
            case PRIMITIVE_LONG:
            case PRIMITIVE_SHORT:
            case PRIMITIVE_BOOLEAN:
            case STRING:
                return new SimpleElementValueGen((SimpleElementValue) value, constantPool);
            case ENUM_CONSTANT:
                return new EnumElementValueGen(ENUM_CONSTANT, (EnumElementValue) value, constantPool);
            case CLASS:
                return new ClassElementValueGen(CLASS, (ClassElementValue) value, constantPool);
            case ANNOTATION:
                return new AnnotationElementValueGen(ANNOTATION, (AnnotationElementValue) value);
            case ARRAY:
                return new ArrayElementValueGen(ARRAY, (ArrayElementValue) value, constantPool);
            default:
                throw new IllegalArgumentException("Unexpected element value kind in annotation: " + value.getType());
        }
    }
}
