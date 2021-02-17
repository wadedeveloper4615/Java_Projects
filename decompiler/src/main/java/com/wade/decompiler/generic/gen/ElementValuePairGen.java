package com.wade.decompiler.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.classfile.element.ElementValuePair;

public class ElementValuePairGen {
    private int nameIdx;
    private ElementValueGen value;
    private ConstantPoolGen constantPoolGen;

    public ElementValuePairGen(ElementValuePair nvp, ConstantPoolGen cpool, boolean copyPoolEntries) {
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

    public ElementValuePairGen(int idx, ElementValueGen value, ConstantPoolGen cpool) {
        this.nameIdx = idx;
        this.value = value;
        this.constantPoolGen = cpool;
    }

    public ElementValuePairGen(String name, ElementValueGen value, ConstantPoolGen cpool) {
        this.nameIdx = cpool.addUtf8(name);
        this.value = value;
        this.constantPoolGen = cpool;
    }

    public void dump(DataOutputStream dos) throws IOException {
        dos.writeShort(nameIdx); // u2 name of the element
        value.dump(dos);
    }

    public ElementValuePair getElementNameValuePair() {
        ElementValue immutableValue = value.getElementValue();
        return new ElementValuePair(nameIdx, immutableValue, constantPoolGen.getConstantPool());
    }

    public int getNameIndex() {
        return nameIdx;
    }

    public String getNameString() {
        // ConstantString cu8 = (ConstantString)constantPoolGen.getConstant(nameIdx);
        return ((ConstantUtf8) constantPoolGen.getConstant(nameIdx)).getBytes();
    }

    public ElementValueGen getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ElementValuePair:[" + getNameString() + "=" + value.stringifyValue() + "]";
    }
}
