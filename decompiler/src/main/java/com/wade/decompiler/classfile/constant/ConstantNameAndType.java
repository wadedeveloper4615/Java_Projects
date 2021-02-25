package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantNameAndType extends Constant {
    private final int nameIndex;
    private final int signatureIndex;

    public ConstantNameAndType(DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType(int nameIndex, int signatureIndex) {
        super(ClassFileConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
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

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ", signatureIndex = " + signatureIndex + ")";
    }
}
