package com.wade.app.classfile.element;

import com.wade.app.classfile.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class EnumElementValue extends ElementValue {
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

    public int getTypeIdx() {
        return typeIdx;
    }

    public int getValueIdx() {
        return valueIdx;
    }

    @Override
    public String toString() {
        return "EnumElementValue [typeIdx=" + typeIdx + ", valueIdx=" + valueIdx + "]";
    }
}
