package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class ConstantValue extends Attribute {
    private final int constantValueIndex;

    public ConstantValue(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(int name_index, int length, int constantValueIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConstantValue other = (ConstantValue) obj;
        if (constantValueIndex != other.constantValueIndex)
            return false;
        return true;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + constantValueIndex;
        return result;
    }

    @Override
    public String toString() {
        return "ConstantValue [constantValueIndex=" + constantValueIndex + "]";
    }
}
