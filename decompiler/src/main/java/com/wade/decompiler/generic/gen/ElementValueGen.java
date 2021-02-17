package com.wade.decompiler.generic.gen;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.AnnotationElementValue;
import com.wade.decompiler.classfile.AnnotationEntry;
import com.wade.decompiler.classfile.ArrayElementValue;
import com.wade.decompiler.classfile.ClassElementValue;
import com.wade.decompiler.classfile.ElementValue;
import com.wade.decompiler.classfile.EnumElementValue;
import com.wade.decompiler.classfile.SimpleElementValue;

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

    protected ElementValueGen(int type, ConstantPoolGen cpGen) {
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

    public static ElementValueGen copy(ElementValue value, ConstantPoolGen cpool, boolean copyPoolEntries) {
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

    public static ElementValueGen readElementValue(DataInput dis, ConstantPoolGen cpGen) throws IOException {
        int type = dis.readUnsignedByte();
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
                int numArrayVals = dis.readUnsignedShort();
                ElementValue[] evalues = new ElementValue[numArrayVals];
                for (int j = 0; j < numArrayVals; j++) {
                    evalues[j] = ElementValue.readElementValue(dis, cpGen.getConstantPool());
                }
                return new ArrayElementValueGen(ARRAY, evalues, cpGen);
            default:
                throw new IllegalArgumentException("Unexpected element value kind in annotation: " + type);
        }
    }
}
