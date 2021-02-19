package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantDouble extends Constant implements ConstantObject {
    private final double bytes;

    public ConstantDouble(DataInput file) throws IOException {
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
    public Object getConstantValue(ConstantPool cp) {
        return Double.valueOf(bytes);
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
