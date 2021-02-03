package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;

public final class ConstantMethodHandle extends Constant {
    private int referenceKind;
    private int referenceIndex;

    public ConstantMethodHandle(final ConstantMethodHandle c) {
        this(c.getReferenceKind(), c.getReferenceIndex());
    }

    public ConstantMethodHandle(final DataInputStream file) throws IOException {
        this(file.readUnsignedByte(), file.readUnsignedShort());
    }

    public ConstantMethodHandle(final int reference_kind, final int reference_index) {
        super(Const.CONSTANT_MethodHandle);
        this.referenceKind = reference_kind;
        this.referenceIndex = reference_index;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public int getReferenceKind() {
        return referenceKind;
    }
}
