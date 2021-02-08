package com.wade.app.classfile.attribute;

import com.wade.app.constantpool.ConstantPool;

public class ElementValuePair {
    private  ElementValue elementValue;
    private  ConstantPool constantPool;
    private  int elementNameIndex;

    public ElementValuePair(int elementNameIndex, ElementValue elementValue, ConstantPool constantPool) {
        this.elementValue = elementValue;
        this.elementNameIndex = elementNameIndex;
        this.constantPool = constantPool;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getElementNameIndex() {
        return elementNameIndex;
    }

    public ElementValue getElementValue() {
        return elementValue;
    }
}
