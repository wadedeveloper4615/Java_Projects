package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public class ConstantLong extends Constant implements ConstantObject {
    private long bytes;

    public ConstantLong(final ConstantLong c) {
        this(c.getBytes());
    }

    public ConstantLong(final DataInputStream file) throws IOException {
        this(file.readLong());
    }

    public ConstantLong(final long bytes) {
        super(ClassFileConstants.CONSTANT_Long);
        this.bytes = bytes;
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Long.valueOf(bytes);
    }
}
