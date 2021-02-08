package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantString extends Constant {
    private  int stringIndex;

    public ConstantString(DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantString( int stringIndex) {
        super(ClassFileConstants.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }

    @Override
    public String toString() {
        return "ConstantString(stringIndex = " + stringIndex + ")";
    }
}
