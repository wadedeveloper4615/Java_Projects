package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantInteger extends Constant {
    private int bytes;

    public ConstantInteger(DataInputStream file) throws IOException {
        this(file.readInt());
    }

    public ConstantInteger( int bytes) {
        super(ClassFileConstants.CONSTANT_Integer);
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "ConstantInteger(bytes = " + bytes + ")";
    }
}
