package com.wade.app.attribute.element;

import com.wade.app.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class EnumElementValue extends ElementValue {
    private final int typeIdx;

    private final int valueIdx;

    public EnumElementValue(final int type, final int typeIdx, final int valueIdx, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ENUM_CONSTANT) {
            throw new IllegalArgumentException("Only element values of type enum can be built with this ctor - type specified: " + type);
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    public int getTypeIndex() {
        return typeIdx;
    }

    public int getValueIndex() {
        return valueIdx;
    }
}
