package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantModule extends Constant {
    private int nameIndex;

    public ConstantModule(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantModule(final int nameIndex) {
        super(ClassFileConstants.CONSTANT_Module);
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return "ConstantModule(nameIndex = " + nameIndex + ")";
    }
}
