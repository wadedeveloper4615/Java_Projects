package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.ConstantObject;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantLong extends Constant implements ConstantObject {
    private long bytes;

    public ConstantLong(final ConstantLong c) {
        this(c.getBytes());
    }

    public ConstantLong(final DataInput file) throws IOException {
        this(file.readLong());
    }

    public ConstantLong(final long bytes) {
        super(ClassFileConstants.CONSTANT_Long);
        this.bytes = bytes;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantLong(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeLong(bytes);
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Long.valueOf(bytes);
    }

    public void setBytes(final long bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}