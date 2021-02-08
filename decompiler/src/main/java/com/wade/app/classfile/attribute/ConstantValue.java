package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class ConstantValue extends Attribute {
    private int constantValueIndex;

    public ConstantValue(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(int name_index, int length, int constantValueIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
