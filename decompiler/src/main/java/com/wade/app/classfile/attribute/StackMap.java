package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class StackMap extends Attribute {
    private StackMapEntry[] map; // Table of stack map entries

    public StackMap(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (StackMapEntry[]) null, constant_pool);
        int map_length = input.readUnsignedShort();
        map = new StackMapEntry[map_length];
        for (int i = 0; i < map_length; i++) {
            map[i] = new StackMapEntry(input, constant_pool);
        }
    }

    public StackMap(int name_index, int length, StackMapEntry[] map, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_STACK_MAP, name_index, length, constant_pool);
        this.map = map;
    }

}
