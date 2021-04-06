package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.StackMapEntry;
import com.wade.decompiler.classfile.attribute.StackMapType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StackMapEntryGen {
    private int frameType;
    private int byteCodeOffset;
    private StackMapTypeGen[] typesOfLocals;
    private StackMapTypeGen[] typesOfStackItems;

    public StackMapEntryGen(StackMapEntry attribute, ConstantPool constantPool) {
        frameType = attribute.getFrameType();
        byteCodeOffset = attribute.getByteCodeOffset();

        StackMapType[] typesOfLocals = attribute.getTypesOfLocals();
        this.typesOfLocals = new StackMapTypeGen[typesOfLocals.length];
        for (int i = 0; i < typesOfLocals.length; i++) {
            this.typesOfLocals[i] = new StackMapTypeGen(typesOfLocals[i], constantPool);
        }

        StackMapType[] typesOfStackItems = attribute.getTypesOfStackItems();
        this.typesOfStackItems = new StackMapTypeGen[typesOfStackItems.length];
        for (int i = 0; i < typesOfLocals.length; i++) {
            this.typesOfStackItems[i] = new StackMapTypeGen(typesOfStackItems[i], constantPool);
        }
    }
}
