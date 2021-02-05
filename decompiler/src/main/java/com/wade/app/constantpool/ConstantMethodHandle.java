package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantMethodHandle extends Constant {
    private int referenceKind;
    private int referenceIndex;

    public ConstantMethodHandle(final DataInput file) throws IOException {
        this(file.readUnsignedByte(), file.readUnsignedShort());
    }

    public ConstantMethodHandle(int reference_kind, int reference_index) {
        super(ClassFileConstants.CONSTANT_MethodHandle);
        this.referenceKind = reference_kind;
        this.referenceIndex = reference_index;
    }

    @Override
    public String toString() {
        return "ConstantMethodHandle(referenceKind = " + referenceKind + ", referenceIndex = " + referenceIndex + ")";
    }
}
