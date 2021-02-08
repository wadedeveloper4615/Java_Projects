package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(int name_index, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }
}
