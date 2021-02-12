package org.apache.bcel.classfile.element;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;

public class ArrayElementValue extends ElementValue {
    private final ElementValue[] elementValues;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
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

    public ArrayElementValue(final int type, final ElementValue[] datums, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException("Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = datums;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType());
        dos.writeShort(elementValues.length);
        for (final ElementValue evalue : elementValues) {
            evalue.dump(dos);
        }
    }

    @Override
    public String stringifyValue() {
        final StringBuilder sb = new StringBuilder();
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

    public ElementValue[] getElementValuesArray() {
        return elementValues;
    }

    public int getElementValuesArraySize() {
        return elementValues.length;
    }
}
