package com.wade.decompiler.classfile.element;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;

public class ArrayElementValue extends ElementValue {
    // For array types, this is the array
    private ElementValue[] elementValues;

    public ArrayElementValue(int type, ElementValue[] datums, ConstantPool cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = datums;
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType()); // u1 type of value (ARRAY == '[')
        dos.writeShort(elementValues.length);
        for (ElementValue evalue : elementValues) {
            evalue.dump(dos);
        }
    }

    public ElementValue[] getElementValuesArray() {
        return elementValues;
    }

    public int getElementValuesArraySize() {
        return elementValues.length;
    }

    @Override
    public String stringifyValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elementValues.length; i++) {
            sb.append(elementValues[i].stringifyValue());
            if ((i + 1) < elementValues.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < elementValues.length; i++) {
            sb.append(elementValues[i]);
            if ((i + 1) < elementValues.length) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
