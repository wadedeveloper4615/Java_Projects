package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ArrayElementValue;
import com.wade.decompiler.classfile.element.ElementValue;

import java.util.Arrays;

public class ArrayElementValueGen extends ElementValueGen {
    private ElementValueGen[] value;

    public ArrayElementValueGen(byte type, ArrayElementValue element, ConstantPool constantPool) {
        super(type);
        ElementValue[] value = element.getElementValues();
        this.value = new ElementValueGen[value.length];
        for (int i = 0; i < value.length; i++) {
            this.value[i] = ElementValueGen.readElementValue(value[i], constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ArrayElementValueGen other = (ArrayElementValueGen) obj;
        if (!Arrays.equals(value, other.value)) return false;
        return true;
    }

    public ElementValueGen[] getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "ArrayElementValueGen [value=" + Arrays.toString(value) + "]";
    }
}
