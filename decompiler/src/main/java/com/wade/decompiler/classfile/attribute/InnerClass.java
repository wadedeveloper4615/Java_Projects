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
    public String toString() {
        return "InnerClass(" + innerClassIndex + ", " + outerClassIndex + ", " + innerNameIndex + ", " + innerAccessFlags + ")";
    }
}
