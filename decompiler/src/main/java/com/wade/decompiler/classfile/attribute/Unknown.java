package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Unknown extends Attribute {
    private byte[] bytes;

    public Unknown(int nameIndex, int length, byte[] bytes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_UNKNOWN, nameIndex, length, constant_pool);
        this.bytes = bytes;
    }

    public Unknown(int nameIndex, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(nameIndex, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Unknown other = (Unknown) obj;
        if (!Arrays.equals(bytes, other.bytes))
            return false;
        return true;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(bytes);
        return result;
    }

    @Override
    public String toString() {
        return "Unknown [bytes=" + Arrays.toString(bytes) + "]";
    }
}
