package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class Deprecated extends Attribute {
    private byte[] bytes;

    public Deprecated(final Deprecated c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    public Deprecated(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) {
        super(Const.ATTR_DEPRECATED, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    Deprecated(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
            println("Deprecated attribute with length > 0");
        }
    }

    public byte[] getBytes() {
        return bytes;
    }
}
