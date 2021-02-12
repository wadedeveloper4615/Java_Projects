package org.apache.bcel.generic.gen;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.element.AnnotationElementValue;
import org.apache.bcel.classfile.element.ArrayElementValue;
import org.apache.bcel.classfile.element.ClassElementValue;
import org.apache.bcel.classfile.element.ElementValue;
import org.apache.bcel.classfile.element.EnumElementValue;
import org.apache.bcel.classfile.element.SimpleElementValue;

public abstract class ElementValueGen {
    @Deprecated
    protected int type;
    @Deprecated
    protected ConstantPoolGen cpGen;

    protected ElementValueGen(final int type, final ConstantPoolGen cpGen) {
        this.type = type;
        this.cpGen = cpGen;
    }

    public abstract ElementValue getElementValue();

    public int getElementValueType() {
        return type;
    }

    public abstract String stringifyValue();

    public abstract void dump(DataOutputStream dos) throws IOException;

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

    public static ElementValueGen readElementValue(final DataInput dis, final ConstantPoolGen cpGen) throws IOException {
        final int type = dis.readUnsignedByte();
        switch (type) {
            case 'B':
                return new SimpleElementValueGen(PRIMITIVE_BYTE, dis.readUnsignedShort(), cpGen);
            case 'C':
                return new SimpleElementValueGen(PRIMITIVE_CHAR, dis.readUnsignedShort(), cpGen);
            case 'D':
                return new SimpleElementValueGen(PRIMITIVE_DOUBLE, dis.readUnsignedShort(), cpGen);
            case 'F':
                return new SimpleElementValueGen(PRIMITIVE_FLOAT, dis.readUnsignedShort(), cpGen);
            case 'I':
                return new SimpleElementValueGen(PRIMITIVE_INT, dis.readUnsignedShort(), cpGen);
            case 'J':
                return new SimpleElementValueGen(PRIMITIVE_LONG, dis.readUnsignedShort(), cpGen);
            case 'S':
                return new SimpleElementValueGen(PRIMITIVE_SHORT, dis.readUnsignedShort(), cpGen);
            case 'Z':
                return new SimpleElementValueGen(PRIMITIVE_BOOLEAN, dis.readUnsignedShort(), cpGen);
            case 's':
                return new SimpleElementValueGen(STRING, dis.readUnsignedShort(), cpGen);
            case 'e':
                return new EnumElementValueGen(dis.readUnsignedShort(), dis.readUnsignedShort(), cpGen);
            case 'c':
                return new ClassElementValueGen(dis.readUnsignedShort(), cpGen);
            case '@':
                return new AnnotationElementValueGen(ANNOTATION, new AnnotationEntryGen(AnnotationEntry.read(dis, cpGen.getConstantPool(), true), cpGen, false), cpGen);
            case '[':
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

    protected ConstantPoolGen getConstantPool() {
        return cpGen;
    }

    public static ElementValueGen copy(final ElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        switch (value.getElementValueType()) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                return new SimpleElementValueGen((SimpleElementValue) value, cpool, copyPoolEntries);
            case 'e':
                return new EnumElementValueGen((EnumElementValue) value, cpool, copyPoolEntries);
            case '@':
                return new AnnotationElementValueGen((AnnotationElementValue) value, cpool, copyPoolEntries);
            case '[':
                return new ArrayElementValueGen((ArrayElementValue) value, cpool, copyPoolEntries);
            case 'c':
                return new ClassElementValueGen((ClassElementValue) value, cpool, copyPoolEntries);
            default:
                throw new UnsupportedOperationException("Not implemented yet! (" + value.getElementValueType() + ")");
        }
    }
}
