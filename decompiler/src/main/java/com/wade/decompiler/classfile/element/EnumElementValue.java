package com.wade.decompiler.classfile.element;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class EnumElementValue extends ElementValue {
    // For enum types, these two indices point to the type and value
    private int typeIdx;
    private int valueIdx;

    public EnumElementValue(int type, int typeIdx, int valueIdx, ConstantPool cpool) {
        super(type, cpool);
        if (type != ENUM_CONSTANT) {
            throw new IllegalArgumentException("Only element values of type enum can be built with this ctor - type specified: " + type);
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    public String getEnumTypeString() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(typeIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();// Utility.signatureToString(cu8.getBytes());
    }

    public String getEnumValueString() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }

    public int getTypeIndex() {
        return typeIdx;
    }

    public int getValueIndex() {
        return valueIdx;
    }

    @Override
    public String stringifyValue() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}
