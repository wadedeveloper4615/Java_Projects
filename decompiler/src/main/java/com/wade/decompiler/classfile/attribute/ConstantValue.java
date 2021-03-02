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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConstantValue extends Attribute {
    private final int constantValueIndex;

    public ConstantValue(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, input.readUnsignedShort(), constantPool);
    }

    public ConstantValue(int nameIndex, int length, int constantValueIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, nameIndex, length, constantPool);
        this.constantValueIndex = constantValueIndex;
    }
}
