package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

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
        Deprecated other = (Deprecated) obj;
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
        return ClassFileAttributes.ATTR_DEPRECATED.getName();
    }
}
