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
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
public class ConstantFloat extends Constant {
    private final float bytes;

    public ConstantFloat(DataInput file) throws IOException {
        this(file.readFloat());
    }

    public ConstantFloat(float bytes) {
        super(ClassFileConstants.CONSTANT_Float);
        this.bytes = bytes;
    }
}
