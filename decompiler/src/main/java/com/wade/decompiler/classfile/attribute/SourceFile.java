package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(int name_index, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        SourceFile other = (SourceFile) obj;
        if (sourceFileIndex != other.sourceFileIndex)
            return false;
        return true;
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + sourceFileIndex;
        return result;
    }

    @Override
    public String toString() {
        return "SourceFile [sourceFileIndex=" + sourceFileIndex + "]";
    }
}
