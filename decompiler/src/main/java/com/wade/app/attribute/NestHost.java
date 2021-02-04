package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class NestHost extends Attribute {
    private int hostClassIndex;

    public NestHost(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, 0, constant_pool);
        hostClassIndex = input.readUnsignedShort();
    }

    public NestHost(final int nameIndex, final int length, final int hostClassIndex, final ConstantPool constantPool) {
        super(Const.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.hostClassIndex = hostClassIndex;
    }

    public NestHost(final NestHost c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }

    public void setHostClassIndex(final int hostClassIndex) {
        this.hostClassIndex = hostClassIndex;
    }
}
