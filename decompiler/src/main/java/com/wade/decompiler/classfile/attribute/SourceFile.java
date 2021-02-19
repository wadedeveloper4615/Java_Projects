package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(int name_index, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }

    public SourceFile(SourceFile c) {
        this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    public String getSourceFileName() {
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(sourceFileIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public void setSourceFileIndex(int sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }

    @Override
    public String toString() {
        return "SourceFile: " + getSourceFileName();
    }
}
