package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantFloat extends Constant implements ConstantObject {
    private float bytes;

    public ConstantFloat(ConstantFloat c) {
        this(c.getBytes());
    }

    ConstantFloat(DataInput file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat(float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantFloat(this);
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeFloat(bytes);
    }

    public float getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        return Float.valueOf(bytes);
    }

    public void setBytes(float bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
