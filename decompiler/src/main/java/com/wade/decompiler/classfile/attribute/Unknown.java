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
public class Unknown extends Attribute {
    private byte[] bytes;

    public Unknown(int nameIndex, int length, byte[] bytes, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_UNKNOWN, nameIndex, length, constantPool);
        this.bytes = bytes;
    }

    public Unknown(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (byte[]) null, constantPool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }
}
