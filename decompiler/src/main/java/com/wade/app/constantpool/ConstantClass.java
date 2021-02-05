package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantClass extends Constant {
    private final int nameIndex;

    public ConstantClass(DataInputStream in) throws IOException {
        this(in.readUnsignedShort());
    }

    public ConstantClass(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    @Override
    public String toString() {
        return "ConstantClass(nameIndex = " + nameIndex + ")";
    }
}
