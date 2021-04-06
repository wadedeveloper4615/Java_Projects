package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<StackMapEntryGen> map;

    public StackMapGen(StackMap attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        map = new ArrayList<>();
        for (StackMapEntry entry:attribute.getMap()) {
            map.add(new StackMapEntryGen(entry, constantPool));
        }
    }
}
