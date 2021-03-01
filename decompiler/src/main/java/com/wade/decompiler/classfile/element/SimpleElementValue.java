package com.wade.decompiler.classfile.element;

import com.wade.decompiler.classfile.constant.ConstantDouble;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantInteger;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class SimpleElementValue extends ElementValue {
    private int index;

    public SimpleElementValue(int type, int index, ConstantPool cpool) {
        super(type, cpool);
        this.index = index;
    }

    public boolean getValueBoolean() {
        if (super.getType() != PRIMITIVE_BOOLEAN) {
            throw new IllegalStateException("Dont call getValueBoolean() on a non BOOLEAN ElementValue");
        }
        ConstantInteger bo = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
        return bo.getBytes() != 0;
    }

    public byte getValueByte() {
        if (super.getType() != PRIMITIVE_BYTE) {
            throw new IllegalStateException("Dont call getValueByte() on a non BYTE ElementValue");
        }
        ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
        return (byte) c.getBytes();
    }

    public char getValueChar() {
        if (super.getType() != PRIMITIVE_CHAR) {
            throw new IllegalStateException("Dont call getValueChar() on a non CHAR ElementValue");
        }
        ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
        return (char) c.getBytes();
    }

    public double getValueDouble() {
        if (super.getType() != PRIMITIVE_DOUBLE) {
            throw new IllegalStateException("Dont call getValueDouble() on a non DOUBLE ElementValue");
        }
        ConstantDouble d = (ConstantDouble) super.getConstantPool().getConstant(getIndex());
        return d.getBytes();
    }

    public float getValueFloat() {
        if (super.getType() != PRIMITIVE_FLOAT) {
            throw new IllegalStateException("Dont call getValueFloat() on a non FLOAT ElementValue");
        }
        ConstantFloat f = (ConstantFloat) super.getConstantPool().getConstant(getIndex());
        return f.getBytes();
    }

    public int getValueInt() {
        if (super.getType() != PRIMITIVE_INT) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
        return c.getBytes();
    }

    public long getValueLong() {
        if (super.getType() != PRIMITIVE_LONG) {
            throw new IllegalStateException("Dont call getValueLong() on a non LONG ElementValue");
        }
        ConstantLong j = (ConstantLong) super.getConstantPool().getConstant(getIndex());
        return j.getBytes();
    }

    public short getValueShort() {
        if (super.getType() != PRIMITIVE_SHORT) {
            throw new IllegalStateException("Dont call getValueShort() on a non SHORT ElementValue");
        }
        ConstantInteger s = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
        return (short) s.getBytes();
    }

    public String getValueString() {
        if (super.getType() != STRING) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(getIndex(), ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    @Override
    public String stringifyValue() {
        ConstantPool cpool = super.getConstantPool();
        int _type = super.getType();
        switch (_type) {
            case PRIMITIVE_INT:
                ConstantInteger c = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return Integer.toString(c.getBytes());
            case PRIMITIVE_LONG:
                ConstantLong j = (ConstantLong) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Long);
                return Long.toString(j.getBytes());
            case PRIMITIVE_DOUBLE:
                ConstantDouble d = (ConstantDouble) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Double);
                return Double.toString(d.getBytes());
            case PRIMITIVE_FLOAT:
                ConstantFloat f = (ConstantFloat) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Float);
                return Float.toString(f.getBytes());
            case PRIMITIVE_SHORT:
                ConstantInteger s = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return Integer.toString(s.getBytes());
            case PRIMITIVE_BYTE:
                ConstantInteger b = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return Integer.toString(b.getBytes());
            case PRIMITIVE_CHAR:
                ConstantInteger ch = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                return String.valueOf((char) ch.getBytes());
            case PRIMITIVE_BOOLEAN:
                ConstantInteger bo = (ConstantInteger) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Integer);
                if (bo.getBytes() == 0) {
                    return "false";
                }
                return "true";
            case STRING:
                ConstantUtf8 cu8 = (ConstantUtf8) cpool.getConstant(getIndex(), ClassFileConstants.CONSTANT_Utf8);
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
