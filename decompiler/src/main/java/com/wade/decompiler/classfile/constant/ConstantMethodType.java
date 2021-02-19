package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantMethodType extends Constant {
    private int descriptorIndex;

    public ConstantMethodType(ConstantMethodType c) {
        this(c.getDescriptorIndex());
    }

    public ConstantMethodType(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType(int descriptor_index) {
        super(ClassFileConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptor_index) {
        this.descriptorIndex = descriptor_index;
    }

    @Override
    public String toString() {
        return super.toString() + "(descriptorIndex = " + descriptorIndex + ")";
    }
}
