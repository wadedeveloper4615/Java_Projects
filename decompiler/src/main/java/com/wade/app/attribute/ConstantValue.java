package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class ConstantValue extends Attribute {
    private int constantValueIndex;

    public ConstantValue(final ConstantValue c) {
        this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
    }

    ConstantValue(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(final int name_index, final int length, final int constantValueIndex, final ConstantPool constant_pool) {
        super(Const.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

}
