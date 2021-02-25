package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;

    public PMGClass(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }

    public PMGClass(int name_index, int length, int pmgIndex, int pmgClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PMGClass other = (PMGClass) obj;
        if (pmgClassIndex != other.pmgClassIndex)
            return false;
        if (pmgIndex != other.pmgIndex)
            return false;
        return true;
    }

    public int getPmgClassIndex() {
        return pmgClassIndex;
    }

    public int getPmgIndex() {
        return pmgIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + pmgClassIndex;
        result = prime * result + pmgIndex;
        return result;
    }

    @Override
    public String toString() {
        return "PMGClass [pmgClassIndex=" + pmgClassIndex + ", pmgIndex=" + pmgIndex + "]";
    }
}
