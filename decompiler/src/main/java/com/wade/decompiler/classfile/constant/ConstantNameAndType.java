package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantNameAndType extends Constant {
    private int nameIndex; // Name of field/method
    private int signatureIndex; // and its signature.

    public ConstantNameAndType(ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }

    ConstantNameAndType(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType(int nameIndex, int signatureIndex) {
        super(ClassFileConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantNameAndType(this);
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(nameIndex);
        file.writeShort(signatureIndex);
    }

    public String getName(ConstantPool cp) {
        return cp.constantToString(getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature(ConstantPool cp) {
        return cp.constantToString(getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setSignatureIndex(int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ", signatureIndex = " + signatureIndex + ")";
    }
}
