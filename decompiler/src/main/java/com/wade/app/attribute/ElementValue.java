package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.attribute.element.AnnotationElementValue;
import com.wade.app.attribute.element.ArrayElementValue;
import com.wade.app.attribute.element.ClassElementValue;
import com.wade.app.attribute.element.EnumElementValue;
import com.wade.app.attribute.element.SimpleElementValue;
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

    protected ElementValue(final int type, final ConstantPool cpool) {
        this.type = type;
        this.cpool = cpool;
    }

    protected final ConstantPool getConstantPool() {
        return cpool;
    }

    public int getElementValueType() {
        return type;
    }

    protected final int getType() {
        return type;
    }

    public static ElementValue readElementValue(final DataInputStream input, final ConstantPool cpool) throws IOException {
        final byte type = input.readByte();
        switch (type) {
            case PRIMITIVE_BYTE:
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
                final int numArrayVals = input.readUnsignedShort();
                final ElementValue[] evalues = new ElementValue[numArrayVals];
                for (int j = 0; j < numArrayVals; j++) {
                    evalues[j] = ElementValue.readElementValue(input, cpool);
                }
                return new ArrayElementValue(ARRAY, evalues, cpool);

            default:
                throw new IllegalArgumentException("Unexpected element value kind in annotation: " + type);
        }
    }
}
