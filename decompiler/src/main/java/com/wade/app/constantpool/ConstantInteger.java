package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public class ConstantInteger extends Constant implements ConstantObject {
    private int bytes;

    public ConstantInteger(final ConstantInteger c) {
        this(c.getBytes());
    }

    public ConstantInteger(final DataInputStream file) throws IOException {
        this(file.readInt());
    }

    public ConstantInteger(final int bytes) {
        super(ClassFileConstants.CONSTANT_Integer);
        this.bytes = bytes;
    }

    public int getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Integer.valueOf(bytes);
    }
}
