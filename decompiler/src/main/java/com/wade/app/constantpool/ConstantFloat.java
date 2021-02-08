package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantFloat extends Constant {
    private float bytes;

    public ConstantFloat( DataInputStream file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat( float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "ConstantFloat(bytes = " + bytes + ")";
    }
}
