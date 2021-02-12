package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.ConstantObject;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantDouble extends Constant implements ConstantObject {
    private double bytes;

    public ConstantDouble(final ConstantDouble c) {
        this(c.getBytes());
    }

    public ConstantDouble(final DataInput file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(final double bytes) {
        super(ClassFileConstants.CONSTANT_Double);
        this.bytes = bytes;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantDouble(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeDouble(bytes);
    }

    public double getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return new Double(bytes);
    }

    public void setBytes(final double bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}