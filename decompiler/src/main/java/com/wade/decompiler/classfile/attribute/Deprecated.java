package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Deprecated extends Attribute {
    private byte[] bytes;

    public Deprecated(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_DEPRECATED, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    public Deprecated(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return ClassFileAttributes.ATTR_DEPRECATED.getName();
    }
}
