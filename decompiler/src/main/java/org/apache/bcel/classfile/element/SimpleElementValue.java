package org.apache.bcel.classfile.element;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantDouble;
import org.apache.bcel.classfile.constant.ConstantFloat;
import org.apache.bcel.classfile.constant.ConstantInteger;
import org.apache.bcel.classfile.constant.ConstantLong;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileConstants;

public class SimpleElementValue extends ElementValue {
    private int index;

    public SimpleElementValue(final int type, final int index, final ConstantPool cpool) {
        super(type, cpool);
        this.index = index;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        final int _type = super.getType();
        dos.writeByte(_type); // u1 kind of value
        switch (_type) {
            case PRIMITIVE_INT:
            case PRIMITIVE_BYTE:
            case PRIMITIVE_CHAR:
            case PRIMITIVE_FLOAT:
            case PRIMITIVE_LONG:
            case PRIMITIVE_BOOLEAN:
            case PRIMITIVE_SHORT:
            case PRIMITIVE_DOUBLE:
            case STRING:
                dos.writeShort(getIndex());
                break;
            default:
                throw new IllegalStateException("SimpleElementValue doesnt know how to write out type " + _type);
        }
    }

    public int getIndex() {
        return index;
    }

    public boolean getValueBoolean() {
        if (super.getType() != PRIMITIVE_BOOLEAN) {
            throw new IllegalStateException("Dont call getValueBoolean() on a non BOOLEAN ElementValue");
        }
        final ConstantInteger bo = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
        return bo.getBytes() != 0;
    }

    public byte getValueByte() {
        if (super.getType() != PRIMITIVE_BYTE) {
            throw new IllegalStateException("Dont call getValueByte() on a non BYTE ElementValue");
        }
        final ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
        return (byte) c.getBytes();
    }

    public char getValueChar() {
        if (super.getType() != PRIMITIVE_CHAR) {
            throw new IllegalStateException("Dont call getValueChar() on a non CHAR ElementValue");
        }
        final ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
        return (char) c.getBytes();
    }

    public double getValueDouble() {
        if (super.getType() != PRIMITIVE_DOUBLE) {
            throw new IllegalStateException("Dont call getValueDouble() on a non DOUBLE ElementValue");
        }
        final ConstantDouble d = (ConstantDouble) super.getConstantPool().getConstant(getIndex());
        return d.getBytes();
    }

    public float getValueFloat() {
        if (super.getType() != PRIMITIVE_FLOAT) {
            throw new IllegalStateException("Dont call getValueFloat() on a non FLOAT ElementValue");
        }
        final ConstantFloat f = (ConstantFloat) super.getConstantPool().getConstant(getIndex());
        return f.getBytes();
    }

    public int getValueInt() {
        if (super.getType() != PRIMITIVE_INT) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        final ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
        return c.getBytes();
    }

    public long getValueLong() {
        if (super.getType() != PRIMITIVE_LONG) {
            throw new IllegalStateException("Dont call getValueLong() on a non LONG ElementValue");
        }
        final ConstantLong j = (ConstantLong) super.getConstantPool().getConstant(getIndex());
        return j.getBytes();
    }

    public short getValueShort() {
        if (super.getType() != PRIMITIVE_SHORT) {
            throw new IllegalStateException("Dont call getValueShort() on a non SHORT ElementValue");
        }
        final ConstantInteger s = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
        return (short) s.getBytes();
    }

    public String getValueString() {
        if (super.getType() != STRING) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    // Whatever kind of value it is, return it as a string
    @Override
    public String stringifyValue() {
        final ConstantPool cpool = super.getConstantPool();
        final int _type = super.getType();
        switch (_type) {
            case PRIMITIVE_INT:
                final ConstantInteger c = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return Integer.toString(c.getBytes());
            case PRIMITIVE_LONG:
                final ConstantLong j = (ConstantLong) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Long);
                return Long.toString(j.getBytes());
            case PRIMITIVE_DOUBLE:
                final ConstantDouble d = (ConstantDouble) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Double);
                return Double.toString(d.getBytes());
            case PRIMITIVE_FLOAT:
                final ConstantFloat f = (ConstantFloat) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Float);
                return Float.toString(f.getBytes());
            case PRIMITIVE_SHORT:
                final ConstantInteger s = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return Integer.toString(s.getBytes());
            case PRIMITIVE_BYTE:
                final ConstantInteger b = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return Integer.toString(b.getBytes());
            case PRIMITIVE_CHAR:
                final ConstantInteger ch = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return String.valueOf((char) ch.getBytes());
            case PRIMITIVE_BOOLEAN:
                final ConstantInteger bo = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                if (bo.getBytes() == 0) {
                    return "false";
                }
                return "true";
            case STRING:
                final ConstantUtf8 cu8 = (ConstantUtf8) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Utf8);
                return cu8.getBytes();
            default:
                throw new IllegalStateException("SimpleElementValue class does not know how to stringify type " + _type);
        }
    }

    @Override
    public String toString() {
        return stringifyValue();
    }
}
