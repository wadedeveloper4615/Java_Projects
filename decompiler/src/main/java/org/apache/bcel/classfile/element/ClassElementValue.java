package org.apache.bcel.classfile.element;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileConstants;

public class ClassElementValue extends ElementValue {
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
