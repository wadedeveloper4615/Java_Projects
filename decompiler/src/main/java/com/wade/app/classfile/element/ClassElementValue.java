package com.wade.app.classfile.element;

import com.wade.app.classfile.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class ClassElementValue extends ElementValue {
    private int index;

    public ClassElementValue(int type, int index, ConstantPool cpool) {
        super(type, cpool);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "ClassElementValue [index=" + index + "]";
    }
}
