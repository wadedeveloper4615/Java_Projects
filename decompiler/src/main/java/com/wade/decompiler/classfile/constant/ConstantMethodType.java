package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConstantMethodType extends Constant {
    private final int descriptorIndex;

    public ConstantMethodType(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType(int descriptor_index) {
        super(ClassFileConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }
}
