package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.*;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Deprecated;

public abstract class ElementValueGen {
    public static final int STRING = 's';
    public static final int ENUM_CONSTANT = 'e';
    public static final int CLASS = 'c';
    public static final int ANNOTATION = '@';
    public static final int ARRAY = '[';
    public static final int PRIMITIVE_INT = 'I';
    public static final int PRIMITIVE_BYTE = 'B';
    public static final int PRIMITIVE_CHAR = 'C';
    public static final int PRIMITIVE_DOUBLE = 'D';
    public static final int PRIMITIVE_FLOAT = 'F';
    public static final int PRIMITIVE_LONG = 'J';
    public static final int PRIMITIVE_SHORT = 'S';
    public static final int PRIMITIVE_BOOLEAN = 'Z';
    @Deprecated
    protected int type;
    @Deprecated
    protected ConstantPoolGen cpGen;

    protected ElementValueGen(final int type, final ConstantPoolGen cpGen) {
        this.type = type;
        this.cpGen = cpGen;
    }

    public abstract void dump(DataOutputStream dos) throws IOException;

    protected ConstantPoolGen getConstantPool() {
        return cpGen;
    }

    public abstract ElementValue getElementValue();

    public int getElementValueType() {
        return type;
    }

    public abstract String stringifyValue();

    public static ElementValueGen copy(final ElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        switch (value.getElementValueType()) {
            case 'B': // byte
            case 'C': // char
            case 'D': // double
            case 'F': // float
            case 'I': // int
            case 'J': // long
            case 'S': // short
            case 'Z': // boolean
            case 's': // String
                return new SimpleElementValueGen((SimpleElementValue) value, cpool, copyPoolEntries);
            case 'e': // Enum constant
                return new EnumElementValueGen((EnumElementValue) value, cpool, copyPoolEntries);
            case '@': // Annotation
                return new AnnotationElementValueGen((AnnotationElementValue) value, cpool, copyPoolEntries);
            case '[': // Array
                return new ArrayElementValueGen((ArrayElementValue) value, cpool, copyPoolEntries);
            case 'c': // Class
                return new ClassElementValueGen((ClassElementValue) value, cpool, copyPoolEntries);
            default:
                throw new UnsupportedOperationException("Not implemented yet! (" + value.getElementValueType() + ")");
        }
    }

    public static ElementValueGen readElementValue(final DataInput dis, final ConstantPoolGen cpGen) throws IOException {
        final int type = dis.readUnsignedByte();
        switch (type) {
            case 'B': // byte
                return new SimpleElementValueGen(PRIMITIVE_BYTE, dis.readUnsignedShort(), cpGen);
            case 'C': // char
                return new SimpleElementValueGen(PRIMITIVE_CHAR, dis.readUnsignedShort(), cpGen);
            case 'D': // double
                return new SimpleElementValueGen(PRIMITIVE_DOUBLE, dis.readUnsignedShort(), cpGen);
            case 'F': // float
                return new SimpleElementValueGen(PRIMITIVE_FLOAT, dis.readUnsignedShort(), cpGen);
            case 'I': // int
                return new SimpleElementValueGen(PRIMITIVE_INT, dis.readUnsignedShort(), cpGen);
            case 'J': // long
                return new SimpleElementValueGen(PRIMITIVE_LONG, dis.readUnsignedShort(), cpGen);
            case 'S': // short
                return new SimpleElementValueGen(PRIMITIVE_SHORT, dis.readUnsignedShort(), cpGen);
            case 'Z': // boolean
                return new SimpleElementValueGen(PRIMITIVE_BOOLEAN, dis.readUnsignedShort(), cpGen);
            case 's': // String
                return new SimpleElementValueGen(STRING, dis.readUnsignedShort(), cpGen);
            case 'e': // Enum constant
                return new EnumElementValueGen(dis.readUnsignedShort(), dis.readUnsignedShort(), cpGen);
            case 'c': // Class
                return new ClassElementValueGen(dis.readUnsignedShort(), cpGen);
            case '@': // Annotation
                // TODO: isRuntimeVisible ??????????
                // FIXME
                return new AnnotationElementValueGen(ANNOTATION, new AnnotationEntryGen(AnnotationEntry.read(dis, cpGen.getConstantPool(), true), cpGen, false), cpGen);
            case '[': // Array
                final int numArrayVals = dis.readUnsignedShort();
                final ElementValue[] evalues = new ElementValue[numArrayVals];
                for (int j = 0; j < numArrayVals; j++) {
                    evalues[j] = ElementValue.readElementValue(dis, cpGen.getConstantPool());
                }
                return new ArrayElementValueGen(ARRAY, evalues, cpGen);
            default:
                throw new IllegalArgumentException("Unexpected element value kind in annotation: " + type);
        }
    }
}