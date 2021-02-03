package com.wade.app.attribute.element;

import com.wade.app.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class ArrayElementValue extends ElementValue {
    private final ElementValue[] elementValues;

    public ArrayElementValue(final int type, final ElementValue[] datums, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = datums;
    }

    public ElementValue[] getElementValuesArray() {
        return elementValues;
    }

    public int getElementValuesArraySize() {
        return elementValues.length;
    }
}
