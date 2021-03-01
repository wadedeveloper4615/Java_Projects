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
@ToString
@EqualsAndHashCode(callSuper = false)
public class ConstantMethodHandle extends Constant {
    private final int referenceKind;
    private final int referenceIndex;

    public ConstantMethodHandle(DataInput file) throws IOException {
        this(file.readUnsignedByte(), file.readUnsignedShort());
    }

    public ConstantMethodHandle(int reference_kind, int reference_index) {
        super(ClassFileConstants.CONSTANT_MethodHandle);
        this.referenceKind = reference_kind;
        this.referenceIndex = reference_index;
    }
}
