package com.wade.decompiler.classfile.element;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class ElementValuePair {
    private ElementValue elementValue;
    private ConstantPool constantPool;
    private int elementNameIndex;

    public ElementValuePair(int elementNameIndex, ElementValue elementValue, ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementNameIndex = elementNameIndex;
        this.constantPool = constantPool;
    }

    public void dump(DataOutputStream dos) throws IOException {
        dos.writeShort(elementNameIndex); // u2 name of the element
        elementValue.dump(dos);
    }

    public int getNameIndex() {
        return elementNameIndex;
    }

    public String getNameString() {
        ConstantUtf8 c = (ConstantUtf8) constantPool.getConstant(elementNameIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public ElementValue getValue() {
        return elementValue;
    }

    public String toShortString() {
        StringBuilder result = new StringBuilder();
        result.append(getNameString()).append("=").append(getValue().toShortString());
        return result.toString();
    }
}
