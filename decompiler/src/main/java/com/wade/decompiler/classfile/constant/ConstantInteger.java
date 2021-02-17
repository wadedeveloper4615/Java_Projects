package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantInteger extends Constant implements ConstantObject {
    private int bytes;

    public ConstantInteger(ConstantInteger c) {
        this(c.getBytes());
    }

    ConstantInteger(DataInput file) throws IOException {
        this(file.readInt());
    }

    public ConstantInteger(int bytes) {
        super(ClassFileConstants.CONSTANT_Integer);
        this.bytes = bytes;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantInteger(this);
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeInt(bytes);
    }

    public int getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        return Integer.valueOf(bytes);
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
