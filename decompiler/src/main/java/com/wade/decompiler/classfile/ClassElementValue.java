package com.wade.decompiler.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ClassElementValue extends ElementValue {
    // For primitive types and string type, this points to the value entry in
    // the cpool
    // For 'class' this points to the class entry in the cpool
    private final int idx;

    public ClassElementValue(final int type, final int idx, final ConstantPool cpool) {
        super(type, cpool);
        this.idx = idx;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType()); // u1 kind of value
        dos.writeShort(idx);
    }

    public String getClassString() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(idx, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public String stringifyValue() {
        final ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(idx, ClassFileConstants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}
