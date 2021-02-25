package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class ConstantValue extends Attribute {
    private final int constantValueIndex;

    public ConstantValue(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, input.readUnsignedShort(), constantPool);
    }

    public ConstantValue(int nameIndex, int length, int constantValueIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, nameIndex, length, constantPool);
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
