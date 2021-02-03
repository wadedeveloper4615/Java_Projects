package com.wade.app.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class Synthetic extends Attribute {
    private byte[] bytes;

    public Synthetic(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) {
        super(Const.ATTR_SYNTHETIC, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    public Synthetic(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
            println("Synthetic attribute with length > 0");
        }
    }

    public Synthetic(final Synthetic c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    public byte[] getBytes() {
        return bytes;
    }
}
