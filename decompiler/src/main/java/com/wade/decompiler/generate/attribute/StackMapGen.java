package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.StackMap;
import com.wade.decompiler.classfile.attribute.StackMapEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class StackMapGen extends AttributeGen {
    private StackMapEntryGen[] map;

    public StackMapGen(StackMap attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        StackMapEntry[] map = attribute.getStackMap();
        int map_length = map.length;
        this.map = new StackMapEntryGen[map_length];
        for (int i = 0; i < map_length; i++) {
            this.map[i] = new StackMapEntryGen(map[i], constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StackMapGen other = (StackMapGen) obj;
        if (!Arrays.equals(map, other.map))
            return false;
        return true;
    }

    public StackMapEntryGen[] getMap() {
        return map;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(map);
        return result;
    }

    @Override
    public String toString() {
        return "StackMapGen [map=" + Arrays.toString(map) + "]";
    }
}
