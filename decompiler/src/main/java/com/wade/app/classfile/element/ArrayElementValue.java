package com.wade.app.classfile.element;

import java.util.Arrays;

import com.wade.app.classfile.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class ArrayElementValue extends ElementValue {
    private ElementValue[] elementValues;

    public ArrayElementValue(int type, ElementValue[] datums, ConstantPool cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = datums;
    }

    public ElementValue[] getElementValues() {
        return elementValues;
    }

    @Override
    public String toString() {
        return "ArrayElementValue [elementValues=" + Arrays.toString(elementValues) + "]";
    }

}
