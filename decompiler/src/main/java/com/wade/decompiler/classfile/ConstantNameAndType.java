package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public final class ConstantNameAndType extends Constant {
    private int nameIndex; // Name of field/method
    private int signatureIndex; // and its signature.

    public ConstantNameAndType(final ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }

    ConstantNameAndType(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType(final int nameIndex, final int signatureIndex) {
        super(Const.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantNameAndType(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(nameIndex);
        file.writeShort(signatureIndex);
    }

    public String getName(final ConstantPool cp) {
        return cp.constantToString(getNameIndex(), Const.CONSTANT_Utf8);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature(final ConstantPool cp) {
        return cp.constantToString(getSignatureIndex(), Const.CONSTANT_Utf8);
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setSignatureIndex(final int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ", signatureIndex = " + signatureIndex + ")";
    }
}