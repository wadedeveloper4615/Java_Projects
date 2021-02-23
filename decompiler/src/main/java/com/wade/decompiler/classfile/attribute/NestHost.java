package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class NestHost extends Attribute {
    private int hostClassIndex;

    public NestHost(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, 0, constant_pool);
        hostClassIndex = input.readUnsignedShort();
    }

    public NestHost(int nameIndex, int length, int hostClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.hostClassIndex = hostClassIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        NestHost other = (NestHost) obj;
        if (hostClassIndex != other.hostClassIndex)
            return false;
        return true;
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + hostClassIndex;
        return result;
    }

    @Override
    public String toString() {
        return "NestHost [hostClassIndex=" + hostClassIndex + "]";
    }
}
