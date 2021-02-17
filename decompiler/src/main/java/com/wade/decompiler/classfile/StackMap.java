package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public void accept(Visitor v) {
        v.visitStackMap(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        StackMap c = (StackMap) clone();
        c.map = new StackMapEntry[map.length];
        for (int i = 0; i < map.length; i++) {
            c.map[i] = map[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(map.length);
        for (StackMapEntry entry : map) {
            entry.dump(file);
        }
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
