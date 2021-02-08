package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.enums.ClassFileAttributes;
import com.wade.app.enums.ClassFileConstants;

public class SourceFile extends Attribute {
    private int sourceFileIndex;
    private String name;

    public SourceFile(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(int name_index, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
        this.name = ((ConstantUtf8) constantPool.getConstant(sourceFileIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    @Override
    public String toString() {
        return "SourceFile [name=" + name + "]";
    }
}
