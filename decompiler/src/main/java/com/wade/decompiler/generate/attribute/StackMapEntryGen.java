package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.StackMapEntry;
import com.wade.decompiler.classfile.attribute.StackMapType;
import com.wade.decompiler.classfile.constant.ConstantPool;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        StackMapEntryGen other = (StackMapEntryGen) obj;
        if (byteCodeOffset != other.byteCodeOffset) return false;
        if (frameType != other.frameType) return false;
        if (!Arrays.equals(typesOfLocals, other.typesOfLocals)) return false;
        if (!Arrays.equals(typesOfStackItems, other.typesOfStackItems)) return false;
        return true;
    }

    public int getByteCodeOffset() {
        return byteCodeOffset;
    }

    public int getFrameType() {
        return frameType;
    }

    public StackMapTypeGen[] getTypesOfLocals() {
        return typesOfLocals;
    }

    public StackMapTypeGen[] getTypesOfStackItems() {
        return typesOfStackItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + byteCodeOffset;
        result = prime * result + frameType;
        result = prime * result + Arrays.hashCode(typesOfLocals);
        result = prime * result + Arrays.hashCode(typesOfStackItems);
        return result;
    }

    @Override
    public String toString() {
        return "StackMapEntryGen [frameType=" + frameType + ", byteCodeOffset=" + byteCodeOffset + ", typesOfLocals=" + Arrays.toString(typesOfLocals) + ", typesOfStackItems=" + Arrays.toString(typesOfStackItems) + "]";
    }
}
