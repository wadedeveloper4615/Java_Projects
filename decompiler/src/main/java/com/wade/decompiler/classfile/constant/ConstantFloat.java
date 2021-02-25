package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantFloat extends Constant implements ConstantObject {
    private final float bytes;

    public ConstantFloat(DataInputStream file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat(float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }

    public float getBytes() {
        return bytes;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        return Float.valueOf(bytes);
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }
}
