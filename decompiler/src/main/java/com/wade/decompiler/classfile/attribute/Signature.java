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
public class Signature extends Attribute {
    private int signatureIndex;

    public Signature(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, input.readUnsignedShort(), constantPool);
    }

    public Signature(int nameIndex, int length, int signatureIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_SIGNATURE, nameIndex, length, constantPool);
        this.signatureIndex = signatureIndex;
    }
}
