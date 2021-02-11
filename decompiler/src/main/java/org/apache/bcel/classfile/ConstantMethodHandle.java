
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ConstantMethodHandle extends Constant {

    private int referenceKind;
    private int referenceIndex;

    public ConstantMethodHandle(final ConstantMethodHandle c) {
        this(c.getReferenceKind(), c.getReferenceIndex());
    }

    ConstantMethodHandle(final DataInput file) throws IOException {
        this(file.readUnsignedByte(), file.readUnsignedShort());
    }

    public ConstantMethodHandle(final int reference_kind, final int reference_index) {
        super(Const.CONSTANT_MethodHandle);
        this.referenceKind = reference_kind;
        this.referenceIndex = reference_index;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantMethodHandle(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeByte(referenceKind);
        file.writeShort(referenceIndex);
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public void setReferenceKind(final int reference_kind) {
        this.referenceKind = reference_kind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(final int reference_index) {
        this.referenceIndex = reference_index;
    }

    @Override
    public String toString() {
        return super.toString() + "(referenceKind = " + referenceKind + ", referenceIndex = " + referenceIndex + ")";
    }
}
