package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;

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
        super(Const.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    public String getName(ConstantPool cp) throws ClassFormatException {
        return cp.constantToString(getNameIndex(), Const.CONSTANT_Utf8);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature(ConstantPool cp) throws ClassFormatException {
        return cp.constantToString(getSignatureIndex(), Const.CONSTANT_Utf8);
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

}
