package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantNameAndType extends Constant {
    private int nameIndex;
    private int signatureIndex;

    public ConstantNameAndType(DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantNameAndType( int nameIndex,  int signatureIndex) {
        super(ClassFileConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
    }

    @Override
    public String toString() {
        return "ConstantNameAndType(nameIndex = " + nameIndex + ", signatureIndex = " + signatureIndex + ")";
    }
}
