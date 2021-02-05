package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantDouble extends Constant {
    private double bytes;

    public ConstantDouble(final DataInputStream file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(final double bytes) {
        super(ClassFileConstants.CONSTANT_Double);
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "ConstantDouble(bytes = " + bytes + ")";
    }
}
