package com.wade.app.attribute.element;

import com.wade.app.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class ClassElementValue extends ElementValue {
    private final int idx;

    public ClassElementValue(final int type, final int idx, final ConstantPool cpool) {
        super(type, cpool);
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }
}
