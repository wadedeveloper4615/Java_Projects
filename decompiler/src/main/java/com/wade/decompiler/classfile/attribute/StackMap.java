package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StackMap extends Attribute {
    private StackMapEntry[] map;

    public StackMap(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (StackMapEntry[]) null, constantPool);
        int map_length = input.readUnsignedShort();
        map = new StackMapEntry[map_length];
        for (int i = 0; i < map_length; i++) {
            map[i] = new StackMapEntry(input, constantPool);
        }
    }

    public StackMap(int nameIndex, int length, StackMapEntry[] map, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_STACK_MAP, nameIndex, length, constantPool);
        this.map = map;
    }
}
