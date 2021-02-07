package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantPackage extends Constant {
    private final int nameIndex;

    public ConstantPackage(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantPackage(final int nameIndex) {
        super(ClassFileConstants.CONSTANT_Package);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    @Override
    public String toString() {
        return "ConstantPackage(nameIndex = " + nameIndex + ")";
    }
}
