package com.wade.decompiler.classfile.element;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

public class ClassElementValue extends ElementValue {
    // For primitive types and string type, this points to the value entry in
    // the cpool
    // For 'class' this points to the class entry in the cpool
    private int idx;

    public ClassElementValue(int type, int idx, ConstantPool cpool) {
        super(type, cpool);
        this.idx = idx;
    }

    public String getClassString() {
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(idx, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public String stringifyValue() {
        ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(idx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}