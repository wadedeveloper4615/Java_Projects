package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.element.AnnotationElementValue;
import com.wade.app.classfile.element.ArrayElementValue;
import com.wade.app.classfile.element.ClassElementValue;
import com.wade.app.classfile.element.EnumElementValue;
import com.wade.app.classfile.element.SimpleElementValue;
import com.wade.app.constantpool.ConstantPool;

public abstract class ElementValue {
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
    protected ConstantPool cpool;

    protected ElementValue(int type, ConstantPool cpool) {
        this.type = type;
        this.cpool = cpool;
    }

    public static ElementValue readElementValue(DataInputStream input, ConstantPool cpool) throws IOException {
        byte type = input.readByte();
        switch (type) {
            case ElementValue.PRIMITIVE_BYTE:
            case ElementValue.PRIMITIVE_CHAR:
            case ElementValue.PRIMITIVE_DOUBLE:
            case ElementValue.PRIMITIVE_FLOAT:
            case ElementValue.PRIMITIVE_INT:
            case ElementValue.PRIMITIVE_LONG:
            case ElementValue.PRIMITIVE_SHORT:
            case ElementValue.PRIMITIVE_BOOLEAN:
            case ElementValue.STRING:
                return new SimpleElementValue(type, input.readUnsignedShort(), cpool);
            case ElementValue.ENUM_CONSTANT:
                return new EnumElementValue(ENUM_CONSTANT, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
            case ElementValue.CLASS:
                return new ClassElementValue(CLASS, input.readUnsignedShort(), cpool);
            case ElementValue.ANNOTATION:
                return new AnnotationElementValue(ANNOTATION, AnnotationEntry.read(input, cpool, false), cpool);
            case ElementValue.ARRAY:
                int numArrayVals = input.readUnsignedShort();
                ElementValue[] evalues = new ElementValue[numArrayVals];
                for (int j = 0; j < numArrayVals; j++) {
                    evalues[j] = ElementValue.readElementValue(input, cpool);
                }
                return new ArrayElementValue(ARRAY, evalues, cpool);
            default:
                throw new IllegalArgumentException("Unexpected element value kind in annotation: " + type);
        }
    }
}
