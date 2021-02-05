package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public final class ConstantFloat extends Constant implements ConstantObject {
    private float bytes;

    public ConstantFloat(final ConstantFloat c) {
        this(c.getBytes());
    }

    public ConstantFloat(final DataInputStream file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat(final float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }

    public float getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Float.valueOf(bytes);
    }
}
