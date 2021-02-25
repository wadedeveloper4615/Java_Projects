package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantMethodHandle extends Constant {
    private final int referenceKind;
    private final int referenceIndex;

    public ConstantMethodHandle(DataInputStream file) throws IOException {
        this(file.readUnsignedByte(), file.readUnsignedShort());
    }

    public ConstantMethodHandle(int reference_kind, int reference_index) {
        super(ClassFileConstants.CONSTANT_MethodHandle);
        this.referenceKind = reference_kind;
        this.referenceIndex = reference_index;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    @Override
    public String toString() {
        return super.toString() + "(referenceKind = " + referenceKind + ", referenceIndex = " + referenceIndex + ")";
    }
}
