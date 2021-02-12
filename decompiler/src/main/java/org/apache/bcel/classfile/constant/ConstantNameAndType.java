
package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantNameAndType extends Constant {

    private int nameIndex; // Name of field/method
    private int signatureIndex; // and its signature.

    public ConstantNameAndType(final ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }

    public ConstantNameAndType(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType(final int nameIndex, final int signatureIndex) {
        super(ClassFileConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantNameAndType(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(nameIndex);
        file.writeShort(signatureIndex);
    }

    public String getName(final ConstantPool cp) {
        return cp.constantToString(getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature(final ConstantPool cp) {
        return cp.constantToString(getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
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
