package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public final class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(final int name_index, final int length, final int sourceFileIndex, final ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }

    public SourceFile(final SourceFile c) {
        this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitSourceFile(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(sourceFileIndex);
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    public String getSourceFileName() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(sourceFileIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public void setSourceFileIndex(final int sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }

    @Override
    public String toString() {
        return "SourceFile: " + getSourceFileName();
    }
}
