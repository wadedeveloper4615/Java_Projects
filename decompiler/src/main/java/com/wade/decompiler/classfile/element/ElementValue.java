package com.wade.decompiler.classfile.element;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ElementValue other = (ElementValue) obj;
        if (cpool == null) {
            if (other.cpool != null)
                return false;
        } else if (!cpool.equals(other.cpool))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    public ConstantPool getConstantPool() {
        return cpool;
    }

    public int getElementValueType() {
        return type;
    }

    public int getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpool == null) ? 0 : cpool.hashCode());
        result = prime * result + type;
        return result;
    }

    public abstract String stringifyValue();

    public String toShortString() {
        return stringifyValue();
    }

    @Override
    public String toString() {
        return stringifyValue();
    }

    public static ElementValue readElementValue(DataInputStream input, ConstantPool cpool) throws IOException {
        byte type = input.readByte();
        switch (type) {
            case 16:
            case PRIMITIVE_CHAR:
            case PRIMITIVE_DOUBLE:
            case PRIMITIVE_FLOAT:
            case PRIMITIVE_INT:
            case PRIMITIVE_LONG:
            case PRIMITIVE_SHORT:
            case PRIMITIVE_BOOLEAN:
            case STRING:
                return new SimpleElementValue(type, input.readUnsignedShort(), cpool);
            case ENUM_CONSTANT:
                return new EnumElementValue(ENUM_CONSTANT, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
            case CLASS:
                return new ClassElementValue(CLASS, input.readUnsignedShort(), cpool);
            case ANNOTATION:
                return new AnnotationElementValue(ANNOTATION, AnnotationEntry.read(input, cpool, false), cpool);
            case ARRAY:
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
