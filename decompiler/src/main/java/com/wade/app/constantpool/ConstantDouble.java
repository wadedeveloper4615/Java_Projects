package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.Const;

public final class ConstantDouble extends Constant implements ConstantObject {
    private double bytes;

    public ConstantDouble(final ConstantDouble c) {
        this(c.getBytes());
    }

    public ConstantDouble(final DataInput file) throws IOException {
        this(file.readDouble());
    }

    public ConstantDouble(final double bytes) {
        super(Const.CONSTANT_Double);
        this.bytes = bytes;
    }

    public double getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Double.valueOf(bytes);
    }
}
