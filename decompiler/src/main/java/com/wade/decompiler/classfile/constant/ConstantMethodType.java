package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantMethodType extends Constant {
    private final int descriptorIndex;

    public ConstantMethodType(DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType(int descriptor_index) {
        super(ClassFileConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(descriptorIndex = " + descriptorIndex + ")";
    }
}
