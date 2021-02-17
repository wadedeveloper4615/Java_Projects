package com.wade.decompiler.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.ConstantDouble;
import com.wade.decompiler.classfile.ConstantFloat;
import com.wade.decompiler.classfile.ConstantInteger;
import com.wade.decompiler.classfile.ConstantLong;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.classfile.ElementValue;
import com.wade.decompiler.classfile.SimpleElementValue;

public class SimpleElementValueGen extends ElementValueGen {
    // For primitive types and string type, this points to the value entry in
    // the cpGen
    // For 'class' this points to the class entry in the cpGen
    private int idx;

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, boolean value) {
        super(type, cpGen);
        if (value) {
            idx = getConstantPool().addInteger(1);
        } else {
            idx = getConstantPool().addInteger(0);
        }
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, byte value) {
        super(type, cpGen);
        idx = getConstantPool().addInteger(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, char value) {
        super(type, cpGen);
        idx = getConstantPool().addInteger(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, double value) {
        super(type, cpGen);
        idx = getConstantPool().addDouble(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, float value) {
        super(type, cpGen);
        idx = getConstantPool().addFloat(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, int value) {
        super(type, cpGen);
        idx = getConstantPool().addInteger(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, long value) {
        super(type, cpGen);
        idx = getConstantPool().addLong(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, short value) {
        super(type, cpGen);
        idx = getConstantPool().addInteger(value);
    }

    public SimpleElementValueGen(int type, ConstantPoolGen cpGen, String value) {
        super(type, cpGen);
        idx = getConstantPool().addUtf8(value);
    }
    // ctors for each supported type... type could be inferred but for now lets
    // force it to be passed

    public SimpleElementValueGen(int type, int idx, ConstantPoolGen cpGen) {
        super(type, cpGen);
        this.idx = idx;
    }

    public SimpleElementValueGen(SimpleElementValue value, ConstantPoolGen cpool, boolean copyPoolEntries) {
        super(value.getElementValueType(), cpool);
        if (!copyPoolEntries) {
            // J5ASSERT: Could assert value.stringifyValue() is the same as
            // cpool.getConstant(SimpleElementValuevalue.getIndex())
            idx = value.getIndex();
        } else {
            switch (value.getElementValueType()) {
                case STRING:
                    idx = cpool.addUtf8(value.getValueString());
                    break;
                case PRIMITIVE_INT:
                    idx = cpool.addInteger(value.getValueInt());
                    break;
                case PRIMITIVE_BYTE:
                    idx = cpool.addInteger(value.getValueByte());
                    break;
                case PRIMITIVE_CHAR:
                    idx = cpool.addInteger(value.getValueChar());
                    break;
                case PRIMITIVE_LONG:
                    idx = cpool.addLong(value.getValueLong());
                    break;
                case PRIMITIVE_FLOAT:
                    idx = cpool.addFloat(value.getValueFloat());
                    break;
                case PRIMITIVE_DOUBLE:
                    idx = cpool.addDouble(value.getValueDouble());
                    break;
                case PRIMITIVE_BOOLEAN:
                    if (value.getValueBoolean()) {
                        idx = cpool.addInteger(1);
                    } else {
                        idx = cpool.addInteger(0);
                    }
                    break;
                case PRIMITIVE_SHORT:
                    idx = cpool.addInteger(value.getValueShort());
                    break;
                default:
                    throw new IllegalArgumentException("SimpleElementValueGen class does not know how to copy this type " + super.getElementValueType());
            }
        }
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType()); // u1 kind of value
        switch (super.getElementValueType()) {
            case PRIMITIVE_INT:
            case PRIMITIVE_BYTE:
            case PRIMITIVE_CHAR:
            case PRIMITIVE_FLOAT:
            case PRIMITIVE_LONG:
            case PRIMITIVE_BOOLEAN:
            case PRIMITIVE_SHORT:
            case PRIMITIVE_DOUBLE:
            case STRING:
                dos.writeShort(idx);
                break;
            default:
                throw new IllegalStateException("SimpleElementValueGen doesnt know how to write out type " + super.getElementValueType());
        }
    }

    @Override
    public ElementValue getElementValue() {
        return new SimpleElementValue(super.getElementValueType(), idx, getConstantPool().getConstantPool());
    }

    public int getIndex() {
        return idx;
    }

    public int getValueInt() {
        if (super.getElementValueType() != PRIMITIVE_INT) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        ConstantInteger c = (ConstantInteger) getConstantPool().getConstant(idx);
        return c.getBytes();
    }

    public String getValueString() {
        if (super.getElementValueType() != STRING) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        ConstantUtf8 c = (ConstantUtf8) getConstantPool().getConstant(idx);
        return c.getBytes();
    }

    // Whatever kind of value it is, return it as a string
    @Override
    public String stringifyValue() {
        switch (super.getElementValueType()) {
            case PRIMITIVE_INT:
                ConstantInteger c = (ConstantInteger) getConstantPool().getConstant(idx);
                return Integer.toString(c.getBytes());
            case PRIMITIVE_LONG:
                ConstantLong j = (ConstantLong) getConstantPool().getConstant(idx);
                return Long.toString(j.getBytes());
            case PRIMITIVE_DOUBLE:
                ConstantDouble d = (ConstantDouble) getConstantPool().getConstant(idx);
                return Double.toString(d.getBytes());
            case PRIMITIVE_FLOAT:
                ConstantFloat f = (ConstantFloat) getConstantPool().getConstant(idx);
                return Float.toString(f.getBytes());
            case PRIMITIVE_SHORT:
                ConstantInteger s = (ConstantInteger) getConstantPool().getConstant(idx);
                return Integer.toString(s.getBytes());
            case PRIMITIVE_BYTE:
                ConstantInteger b = (ConstantInteger) getConstantPool().getConstant(idx);
                return Integer.toString(b.getBytes());
            case PRIMITIVE_CHAR:
                ConstantInteger ch = (ConstantInteger) getConstantPool().getConstant(idx);
                return Integer.toString(ch.getBytes());
            case PRIMITIVE_BOOLEAN:
                ConstantInteger bo = (ConstantInteger) getConstantPool().getConstant(idx);
                if (bo.getBytes() == 0) {
                    return "false";
                }
                return "true";
            case STRING:
                ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(idx);
                return cu8.getBytes();
            default:
                throw new IllegalStateException("SimpleElementValueGen class does not know how to stringify type " + super.getElementValueType());
        }
    }
}
