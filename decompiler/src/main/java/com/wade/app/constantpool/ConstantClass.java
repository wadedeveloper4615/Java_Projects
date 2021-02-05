package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public class ConstantClass extends Constant {
    private final int nameIndex;

    public ConstantClass(DataInputStream in) throws IOException {
        this(in.readUnsignedShort());
    }

    public ConstantClass(final int nameIndex) {
        super(ClassFileConstants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
