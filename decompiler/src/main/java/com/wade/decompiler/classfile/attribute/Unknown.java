package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Unknown extends Attribute {
    private byte[] bytes;

    public Unknown(int nameIndex, int length, byte[] bytes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_UNKNOWN, nameIndex, length, constant_pool);
        this.bytes = bytes;
    }

    public Unknown(int nameIndex, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(nameIndex, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }

    public byte[] getBytes() {
        return bytes;
    }
}
