package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class Deprecated extends Attribute {
    private byte[] bytes;

    public Deprecated(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_DEPRECATED, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    public Deprecated(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
            System.out.println("Deprecated attribute with length > 0");
        }
    }

    @Override
    public String toString() {
        return "Deprecated [bytes=" + Arrays.toString(bytes) + "]";
    }

}
