package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantMethodType extends Constant {
    private int descriptorIndex;

    public ConstantMethodType( DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType( int descriptor_index) {
        super(ClassFileConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }

    @Override
    public String toString() {
        return "ConstantMethodType(descriptorIndex = " + descriptorIndex + ")";
    }
}
