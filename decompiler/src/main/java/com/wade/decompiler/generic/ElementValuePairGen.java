package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.classfile.ElementValue;
import com.wade.decompiler.classfile.ElementValuePair;

public class ElementValuePairGen {
    private int nameIdx;
    private final ElementValueGen value;
    private final ConstantPoolGen constantPoolGen;

    public ElementValuePairGen(final ElementValuePair nvp, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        this.constantPoolGen = cpool;
        // J5ASSERT:
        // Could assert nvp.getNameString() points to the same thing as
        // constantPoolGen.getConstant(nvp.getNameIndex())
        // if
        // (!nvp.getNameString().equals(((ConstantUtf8)constantPoolGen.getConstant(nvp.getNameIndex())).getBytes()))
        // {
        // throw new IllegalArgumentException("envp buggered");
        // }
        if (copyPoolEntries) {
            nameIdx = cpool.addUtf8(nvp.getNameString());
        } else {
            nameIdx = nvp.getNameIndex();
        }
        value = ElementValueGen.copy(nvp.getValue(), cpool, copyPoolEntries);
    }

    protected ElementValuePairGen(final int idx, final ElementValueGen value, final ConstantPoolGen cpool) {
        this.nameIdx = idx;
        this.value = value;
        this.constantPoolGen = cpool;
    }

    public ElementValuePairGen(final String name, final ElementValueGen value, final ConstantPoolGen cpool) {
        this.nameIdx = cpool.addUtf8(name);
        this.value = value;
        this.constantPoolGen = cpool;
    }

    protected void dump(final DataOutputStream dos) throws IOException {
        dos.writeShort(nameIdx); // u2 name of the element
        value.dump(dos);
    }

    public ElementValuePair getElementNameValuePair() {
        final ElementValue immutableValue = value.getElementValue();
        return new ElementValuePair(nameIdx, immutableValue, constantPoolGen.getConstantPool());
    }

    public int getNameIndex() {
        return nameIdx;
    }

    public final String getNameString() {
        // ConstantString cu8 = (ConstantString)constantPoolGen.getConstant(nameIdx);
        return ((ConstantUtf8) constantPoolGen.getConstant(nameIdx)).getBytes();
    }

    public final ElementValueGen getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ElementValuePair:[" + getNameString() + "=" + value.stringifyValue() + "]";
    }
}
