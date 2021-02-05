package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;
import com.wade.app.exception.ClassFormatException;

public final class ConstantNameAndType extends Constant {
    private int nameIndex;
    private int signatureIndex;

    public ConstantNameAndType(final ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }

    public ConstantNameAndType(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType(final int nameIndex, final int signatureIndex) {
        super(ClassFileConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    public String getName(ConstantPool cp) throws ClassFormatException, IOException {
        return cp.constantToString(getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature(ConstantPool cp) throws ClassFormatException, IOException {
        return cp.constantToString(getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

}
