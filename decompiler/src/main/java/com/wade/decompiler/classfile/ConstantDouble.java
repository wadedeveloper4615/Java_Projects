package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public final class ConstantDouble extends Constant implements ConstantObject {
    private double bytes;

    public ConstantDouble(final ConstantDouble c) {
        this(c.getBytes());
    }

    ConstantDouble(final DataInput file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(final double bytes) {
        super(Const.CONSTANT_Double);
        this.bytes = bytes;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantDouble(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeDouble(bytes);
    }

    public double getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Double.valueOf(bytes);
    }

    public void setBytes(final double bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
