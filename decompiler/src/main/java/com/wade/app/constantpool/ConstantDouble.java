package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantDouble extends Constant {
    private double bytes;

    public ConstantDouble(DataInputStream file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(double bytes) {
        super(ClassFileConstants.CONSTANT_Double);
        this.bytes = bytes;
    }

    public double getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "ConstantDouble(bytes = " + bytes + ")";
    }
}
