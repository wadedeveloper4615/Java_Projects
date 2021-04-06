package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StackMap extends Attribute {
    private List<StackMapEntry> map;

    public StackMap(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (List<StackMapEntry>) null, constantPool);
        int map_length = input.readUnsignedShort();
        map = new ArrayList<>();
        for (int i = 0; i < map_length; i++) {
            map.add(new StackMapEntry(input, constantPool));
        }
    }

    public StackMap(int nameIndex, int length, List<StackMapEntry> map, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_STACK_MAP, nameIndex, length, constantPool);
        this.map = map;
    }
}
