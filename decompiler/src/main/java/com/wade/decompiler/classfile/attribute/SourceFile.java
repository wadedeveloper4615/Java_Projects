package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class SourceFile extends Attribute {
    private int sourceFileIndex;
    private String sourceFile;

    public SourceFile(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public SourceFile(int name_index, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
        this.sourceFile = ((ConstantUtf8) constantPool.getConstant(sourceFileIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    @Override
    public String toString() {
        return "SourceFile [sourceFileIndex=" + sourceFileIndex + ", sourceFile=" + sourceFile + "]";
    }
}
