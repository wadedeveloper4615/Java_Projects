package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;

public class InnerClass {
    private int innerClassIndex;
    private int outerClassIndex;
    private int innerNameIndex;
    private ClassAccessFlagsList innerAccessFlags;

    public InnerClass(DataInput file, ConstantPool constantPool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constantPool);
    }

    public InnerClass(InnerClass c) {
        this(c.getInnerClassIndex(), c.getOuterClassIndex(), c.getInnerNameIndex(), c.getInnerAccessFlags());
    }

    public InnerClass(int innerClassIndex, int outerClassIndex, int innerNameIndex, ClassAccessFlagsList innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = innerAccessFlags;
    }

    public InnerClass(int innerClassIndex, int outerClassIndex, int innerNameIndex, int innerAccessFlags, ConstantPool constantPool) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = new ClassAccessFlagsList(innerAccessFlags);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InnerClass other = (InnerClass) obj;
        if (innerAccessFlags == null) {
            if (other.innerAccessFlags != null)
                return false;
        } else if (!innerAccessFlags.equals(other.innerAccessFlags))
            return false;
        if (innerClassIndex != other.innerClassIndex)
            return false;
        if (innerNameIndex != other.innerNameIndex)
            return false;
        if (outerClassIndex != other.outerClassIndex)
            return false;
        return true;
    }

    public ClassAccessFlagsList getInnerAccessFlags() {
        return innerAccessFlags;
    }

    public int getInnerClassIndex() {
        return innerClassIndex;
    }

    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    public int getOuterClassIndex() {
        return outerClassIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((innerAccessFlags == null) ? 0 : innerAccessFlags.hashCode());
        result = prime * result + innerClassIndex;
        result = prime * result + innerNameIndex;
        result = prime * result + outerClassIndex;
        return result;
    }

    @Override
    public String toString() {
        return "InnerClass(" + innerClassIndex + ", " + outerClassIndex + ", " + innerNameIndex + ", " + innerAccessFlags + ")";
    }
}
