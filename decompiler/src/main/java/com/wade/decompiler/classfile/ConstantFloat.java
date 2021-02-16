package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public final class ConstantFloat extends Constant implements ConstantObject {
    private float bytes;

    public ConstantFloat(final ConstantFloat c) {
        this(c.getBytes());
    }

    ConstantFloat(final DataInput file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat(final float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantFloat(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeFloat(bytes);
    }

    public float getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Float.valueOf(bytes);
    }

    public void setBytes(final float bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
