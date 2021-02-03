package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;

public class ConstantClass extends Constant {
    private final int nameIndex;

    public ConstantClass(DataInputStream dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    public ConstantClass(final int nameIndex) {
        super(Const.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
