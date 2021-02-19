package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantMethodHandle extends Constant {
    private int referenceKind;
    private int referenceIndex;

    public ConstantMethodHandle(ConstantMethodHandle c) {
        this(c.getReferenceKind(), c.getReferenceIndex());
    }

    ConstantMethodHandle(DataInput file) throws IOException {
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

    public void setReferenceIndex(int reference_index) {
        this.referenceIndex = reference_index;
    }

    public void setReferenceKind(int reference_kind) {
        this.referenceKind = reference_kind;
    }

    @Override
    public String toString() {
        return super.toString() + "(referenceKind = " + referenceKind + ", referenceIndex = " + referenceIndex + ")";
    }
}
