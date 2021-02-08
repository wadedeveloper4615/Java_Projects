package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantLong extends Constant {
    private long bytes;

    public ConstantLong(DataInputStream file) throws IOException {
        this(file.readLong());
    }

    public ConstantLong( long bytes) {
        super(ClassFileConstants.CONSTANT_Long);
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "ConstantLong(bytes = " + bytes + ")";
    }
}
