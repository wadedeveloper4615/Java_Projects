package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.StackMap;
import com.wade.decompiler.classfile.attribute.StackMapEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StackMapGen extends AttributeGen {
    private StackMapEntryGen[] map;

    public StackMapGen(StackMap attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        StackMapEntry[] map = attribute.getMap();
        int map_length = map.length;
        this.map = new StackMapEntryGen[map_length];
        for (int i = 0; i < map_length; i++) {
            this.map[i] = new StackMapEntryGen(map[i], constantPool);
        }
    }
}
