package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class SourceFile extends Attribute {
    private int sourceFileIndex;

    public SourceFile(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, input.readUnsignedShort(), constantPool);
    }

    public SourceFile(int nameIndex, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SOURCE_FILE, nameIndex, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }
}
