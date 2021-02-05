package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public final class ConstantMethodType extends Constant {
    private int descriptorIndex;

    public ConstantMethodType(final ConstantMethodType c) {
        this(c.getDescriptorIndex());
    }

    public ConstantMethodType(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType(final int descriptor_index) {
        super(ClassFileConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
