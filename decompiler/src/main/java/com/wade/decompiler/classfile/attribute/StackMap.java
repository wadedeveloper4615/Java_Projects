package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class StackMap extends Attribute {
    private StackMapEntry[] map;

    public StackMap(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
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

    public int getMapLength() {
        return map == null ? 0 : map.length;
    }

    public StackMapEntry[] getStackMap() {
        return map;
    }

    public void setStackMap(StackMapEntry[] map) {
        this.map = map;
        int len = 2; // Length of 'number_of_entries' field prior to the array of stack maps
        for (StackMapEntry element : map) {
            len += element.getMapEntrySize();
        }
        setLength(len);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("StackMap(");
        for (int i = 0; i < map.length; i++) {
            buf.append(map[i]);
            if (i < map.length - 1) {
                buf.append(", ");
            }
        }
        buf.append(')');
        return buf.toString();
    }
}
