package org.apache.bcel.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.classfile.element.ElementValue;
import org.apache.bcel.classfile.element.EnumElementValue;
import org.apache.bcel.generic.ObjectType;

public class EnumElementValueGen extends ElementValueGen {
    private int typeIdx;
    private int valueIdx;

    protected EnumElementValueGen(final int typeIdx, final int valueIdx, final ConstantPoolGen cpool) {
        super(ElementValueGen.ENUM_CONSTANT, cpool);
        if (super.getElementValueType() != ENUM_CONSTANT) {
            throw new IllegalArgumentException("Only element values of type enum can be built with this ctor - type specified: " + super.getElementValueType());
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    @Override
    public ElementValue getElementValue() {
        System.err.println("Duplicating value: " + getEnumTypeString() + ":" + getEnumValueString());
        return new EnumElementValue(super.getElementValueType(), typeIdx, valueIdx, getConstantPool().getConstantPool());
    }

    public EnumElementValueGen(final ObjectType t, final String value, final ConstantPoolGen cpool) {
        super(ElementValueGen.ENUM_CONSTANT, cpool);
        typeIdx = cpool.addUtf8(t.getSignature());
        valueIdx = cpool.addUtf8(value);
    }

    public EnumElementValueGen(final EnumElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        super(ENUM_CONSTANT, cpool);
        if (copyPoolEntries) {
            typeIdx = cpool.addUtf8(value.getEnumTypeString());
            valueIdx = cpool.addUtf8(value.getEnumValueString());
        } else {
            typeIdx = value.getTypeIndex();
            valueIdx = value.getValueIndex();
        }
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType());
        dos.writeShort(typeIdx);
        dos.writeShort(valueIdx);
    }

    @Override
    public String stringifyValue() {
        final ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(valueIdx);
        return cu8.getBytes();
    }

    public String getEnumTypeString() {
        return ((ConstantUtf8) getConstantPool().getConstant(typeIdx)).getBytes();
    }

    public String getEnumValueString() {
        return ((ConstantUtf8) getConstantPool().getConstant(valueIdx)).getBytes();
    }

    public int getValueIndex() {
        return valueIdx;
    }

    public int getTypeIndex() {
        return typeIdx;
    }
}
