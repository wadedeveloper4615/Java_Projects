package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.exception.ClassFormatException;

public final class StackMap extends Attribute {
    private StackMapEntry[] map;

    StackMap(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(name_index, length, (StackMapEntry[]) null, constant_pool);
        final int map_length = input.readUnsignedShort();
        map = new StackMapEntry[map_length];
        for (int i = 0; i < map_length; i++) {
            map[i] = new StackMapEntry(input, constant_pool);
        }
    }

    public StackMap(final int name_index, final int length, final StackMapEntry[] map, final ConstantPool constant_pool) {
        super(Const.ATTR_STACK_MAP, name_index, length, constant_pool);
        this.map = map;
    }

    public int getMapLength() {
        return map == null ? 0 : map.length;
    }

    public StackMapEntry[] getStackMap() {
        return map;
    }
}
