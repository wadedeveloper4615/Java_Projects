package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class Synthetic extends Attribute {
    private byte[] bytes;

    public Synthetic(int nameIndex, int length, byte[] bytes, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SYNTHETIC, nameIndex, length, constantPool);
        this.bytes = bytes;
    }

    public Synthetic(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (byte[]) null, constantPool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }
}
