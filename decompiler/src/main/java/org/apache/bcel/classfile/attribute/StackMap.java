
package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.StackMapEntry;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public final class StackMap extends Attribute {

    private StackMapEntry[] map; // Table of stack map entries

    public StackMap(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
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

    @Override
    public void accept(final Visitor v) {
        v.visitStackMap(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final StackMap c = (StackMap) clone();
        c.map = new StackMapEntry[map.length];
        for (int i = 0; i < map.length; i++) {
            c.map[i] = map[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(map.length);
        for (final StackMapEntry entry : map) {
            entry.dump(file);
        }
    }

    public int getMapLength() {
        return map == null ? 0 : map.length;
    }

    public StackMapEntry[] getStackMap() {
        return map;
    }

    public void setStackMap(final StackMapEntry[] map) {
        this.map = map;
        int len = 2; // Length of 'number_of_entries' field prior to the array of stack maps
        for (final StackMapEntry element : map) {
            len += element.getMapEntrySize();
        }
        setLength(len);
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder("StackMap(");
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
