package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        StackMap other = (StackMap) obj;
        if (!Arrays.equals(map, other.map))
            return false;
        return true;
    }

    public int getMapLength() {
        return map == null ? 0 : map.length;
    }

    public StackMapEntry[] getStackMap() {
        return map;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(map);
        return result;
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
