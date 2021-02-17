package com.wade.decompiler.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.classfile.element.EnumElementValue;
import com.wade.decompiler.generic.type.ObjectType;

public class EnumElementValueGen extends ElementValueGen {
    // For enum types, these two indices point to the type and value
    private int typeIdx;
    private int valueIdx;

    public EnumElementValueGen(EnumElementValue value, ConstantPoolGen cpool, boolean copyPoolEntries) {
        super(ENUM_CONSTANT, cpool);
        if (copyPoolEntries) {
            typeIdx = cpool.addUtf8(value.getEnumTypeString());// was
                                                               // addClass(value.getEnumTypeString());
            valueIdx = cpool.addUtf8(value.getEnumValueString()); // was
                                                                  // addString(value.getEnumValueString());
        } else {
            typeIdx = value.getTypeIndex();
            valueIdx = value.getValueIndex();
        }
    }

    protected EnumElementValueGen(int typeIdx, int valueIdx, ConstantPoolGen cpool) {
        super(ElementValueGen.ENUM_CONSTANT, cpool);
        if (super.getElementValueType() != ENUM_CONSTANT) {
            throw new IllegalArgumentException("Only element values of type enum can be built with this ctor - type specified: " + super.getElementValueType());
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    public EnumElementValueGen(ObjectType t, String value, ConstantPoolGen cpool) {
        super(ElementValueGen.ENUM_CONSTANT, cpool);
        typeIdx = cpool.addUtf8(t.getSignature());// was addClass(t);
        valueIdx = cpool.addUtf8(value);// was addString(value);
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType()); // u1 type of value (ENUM_CONSTANT == 'e')
        dos.writeShort(typeIdx); // u2
        dos.writeShort(valueIdx); // u2
    }

    @Override
    public ElementValue getElementValue() {
        System.err.println("Duplicating value: " + getEnumTypeString() + ":" + getEnumValueString());
        return new EnumElementValue(super.getElementValueType(), typeIdx, valueIdx, getConstantPool().getConstantPool());
    }

    // BCELBUG: Should we need to call utility.signatureToString() on the output
    // here?
    public String getEnumTypeString() {
        // Constant cc = getConstantPool().getConstant(typeIdx);
        // ConstantClass cu8 =
        // (ConstantClass)getConstantPool().getConstant(typeIdx);
        // return
        // ((ConstantUtf8)getConstantPool().getConstant(cu8.getNameIndex())).getBytes();
        return ((ConstantUtf8) getConstantPool().getConstant(typeIdx)).getBytes();
        // return Utility.signatureToString(cu8.getBytes());
    }

    public String getEnumValueString() {
        return ((ConstantUtf8) getConstantPool().getConstant(valueIdx)).getBytes();
        // ConstantString cu8 =
        // (ConstantString)getConstantPool().getConstant(valueIdx);
        // return
        // ((ConstantUtf8)getConstantPool().getConstant(cu8.getStringIndex())).getBytes();
    }

    public int getTypeIndex() {
        return typeIdx;
    }

    public int getValueIndex() {
        return valueIdx;
    }

    @Override
    public String stringifyValue() {
        ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(valueIdx);
        return cu8.getBytes();
        // ConstantString cu8 =
        // (ConstantString)getConstantPool().getConstant(valueIdx);
        // return
        // ((ConstantUtf8)getConstantPool().getConstant(cu8.getStringIndex())).getBytes();
    }
}
