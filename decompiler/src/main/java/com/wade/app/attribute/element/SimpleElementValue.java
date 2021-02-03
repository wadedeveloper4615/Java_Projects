package com.wade.app.attribute.element;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;
import com.wade.app.attribute.ElementValue;
import com.wade.app.constantpool.ConstantDouble;
import com.wade.app.constantpool.ConstantFloat;
import com.wade.app.constantpool.ConstantInteger;
import com.wade.app.constantpool.ConstantLong;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;

public class SimpleElementValue extends ElementValue {
    private int index;

    public SimpleElementValue(final int type, final int index, final ConstantPool cpool) {
        super(type, cpool);
        this.index = index;
    }

    /**
     * @return Value entry index in the cpool
     */
    public int getIndex() {
        return index;
    }

    public boolean getValueBoolean() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_BOOLEAN) {
            throw new IllegalStateException("Dont call getValueBoolean() on a non BOOLEAN ElementValue");
        }
        final ConstantInteger bo = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
        return bo.getBytes() != 0;
    }

    public byte getValueByte() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_BYTE) {
            throw new IllegalStateException("Dont call getValueByte() on a non BYTE ElementValue");
        }
        final ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), Const.CONSTANT_Integer);
        return (byte) c.getBytes();
    }

    public char getValueChar() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_CHAR) {
            throw new IllegalStateException("Dont call getValueChar() on a non CHAR ElementValue");
        }
        final ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), Const.CONSTANT_Integer);
        return (char) c.getBytes();
    }

    public double getValueDouble() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_DOUBLE) {
            throw new IllegalStateException("Dont call getValueDouble() on a non DOUBLE ElementValue");
        }
        final ConstantDouble d = (ConstantDouble) super.getConstantPool().getConstant(getIndex());
        return d.getBytes();
    }

    public float getValueFloat() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_FLOAT) {
            throw new IllegalStateException("Dont call getValueFloat() on a non FLOAT ElementValue");
        }
        final ConstantFloat f = (ConstantFloat) super.getConstantPool().getConstant(getIndex());
        return f.getBytes();
    }

    public int getValueInt() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_INT) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        final ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), Const.CONSTANT_Integer);
        return c.getBytes();
    }

    public long getValueLong() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_LONG) {
            throw new IllegalStateException("Dont call getValueLong() on a non LONG ElementValue");
        }
        final ConstantLong j = (ConstantLong) super.getConstantPool().getConstant(getIndex());
        return j.getBytes();
    }

    public short getValueShort() throws ClassFormatException {
        if (super.getType() != PRIMITIVE_SHORT) {
            throw new IllegalStateException("Dont call getValueShort() on a non SHORT ElementValue");
        }
        final ConstantInteger s = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
        return (short) s.getBytes();
    }

    public String getValueString() throws ClassFormatException {
        if (super.getType() != STRING) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(getIndex(), Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public void setIndex(final int index) {
        this.index = index;
    }
}
