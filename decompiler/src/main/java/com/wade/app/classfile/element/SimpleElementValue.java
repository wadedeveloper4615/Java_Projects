package com.wade.app.classfile.element;

import com.wade.app.classfile.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class SimpleElementValue extends ElementValue {
    private int index;

    public SimpleElementValue(int type, int index, ConstantPool cpool) {
        super(type, cpool);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
