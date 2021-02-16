package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public final class ConstantInteger extends Constant implements ConstantObject {
    private int bytes;

    public ConstantInteger(final ConstantInteger c) {
        this(c.getBytes());
    }

    ConstantInteger(final DataInput file) throws IOException {
        this(file.readInt());
    }

    public ConstantInteger(final int bytes) {
        super(ClassFileConstants.CONSTANT_Integer);
        this.bytes = bytes;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantInteger(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeInt(bytes);
    }

    public int getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Integer.valueOf(bytes);
    }

    public void setBytes(final int bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
