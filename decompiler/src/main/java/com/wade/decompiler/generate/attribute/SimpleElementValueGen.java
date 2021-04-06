package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantDouble;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantInteger;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.SimpleElementValue;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SimpleElementValueGen extends ElementValueGen {
    private Constant indexConstant;

    public SimpleElementValueGen(SimpleElementValue element, ConstantPool constantPool) {
        super(element.getType());
        this.indexConstant = constantPool.getConstant(element.getIndex());
        this.type = element.getType();
    }

    public boolean getValueBoolean() {
        if (super.getType() != PRIMITIVE_BOOLEAN) {
            throw new IllegalStateException("Dont call getValueBoolean() on a non BOOLEAN ElementValue");
        }
        ConstantInteger bo = (ConstantInteger) indexConstant;
        return bo.getBytes() != 0;
    }

    public byte getValueByte() {
        if (super.getType() != PRIMITIVE_BYTE) {
            throw new IllegalStateException("Dont call getValueByte() on a non BYTE ElementValue");
        }
        ConstantInteger c = (ConstantInteger) indexConstant;
        return (byte) c.getBytes();
    }

    public char getValueChar() {
        if (super.getType() != PRIMITIVE_CHAR) {
            throw new IllegalStateException("Dont call getValueChar() on a non CHAR ElementValue");
        }
        ConstantInteger c = (ConstantInteger) indexConstant;
        return (char) c.getBytes();
    }

    public double getValueDouble() {
        if (super.getType() != PRIMITIVE_DOUBLE) {
            throw new IllegalStateException("Dont call getValueDouble() on a non DOUBLE ElementValue");
        }
        ConstantDouble d = (ConstantDouble) indexConstant;
        return d.getBytes();
    }

    public float getValueFloat() {
        if (super.getType() != PRIMITIVE_FLOAT) {
            throw new IllegalStateException("Dont call getValueFloat() on a non FLOAT ElementValue");
        }
        ConstantFloat f = (ConstantFloat) indexConstant;
        return f.getBytes();
    }

    public int getValueInt() {
        if (super.getType() != PRIMITIVE_INT) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        ConstantInteger c = (ConstantInteger) indexConstant;
        return c.getBytes();
    }

    public long getValueLong() {
        if (super.getType() != PRIMITIVE_LONG) {
            throw new IllegalStateException("Dont call getValueLong() on a non LONG ElementValue");
        }
        ConstantLong j = (ConstantLong) indexConstant;
        return j.getBytes();
    }

    public short getValueShort() {
        if (super.getType() != PRIMITIVE_SHORT) {
            throw new IllegalStateException("Dont call getValueShort() on a non SHORT ElementValue");
        }
        ConstantInteger s = (ConstantInteger) indexConstant;
        return (short) s.getBytes();
    }

    public String getValueString() {
        if (super.getType() != STRING) {
            throw new IllegalStateException("Dont call getValueString() on a non STRING ElementValue");
        }
        ConstantUtf8 c = (ConstantUtf8) indexConstant;
        return c.getBytes();
    }

    public String stringifyValue() {
        switch (type) {
            case PRIMITIVE_INT:
                return Integer.toString(((ConstantInteger) indexConstant).getBytes());
            case PRIMITIVE_LONG:
                return Long.toString(((ConstantLong) indexConstant).getBytes());
            case PRIMITIVE_DOUBLE:
                return Double.toString(((ConstantDouble) indexConstant).getBytes());
            case PRIMITIVE_FLOAT:
                return Float.toString(((ConstantFloat) indexConstant).getBytes());
            case PRIMITIVE_SHORT:
                return Integer.toString(((ConstantInteger) indexConstant).getBytes());
            case PRIMITIVE_BYTE:
                return Integer.toString(((ConstantInteger) indexConstant).getBytes());
            case PRIMITIVE_CHAR:
                return Integer.toString(((ConstantInteger) indexConstant).getBytes());
            case PRIMITIVE_BOOLEAN:
                if (((ConstantInteger) indexConstant).getBytes() == 0) {
                    return "false";
                }
                return "true";
            case STRING:
                return ((ConstantUtf8) indexConstant).getBytes();
            default:
                throw new IllegalStateException("SimpleElementValue class does not know how to stringify type " + type);
        }
    }
}
