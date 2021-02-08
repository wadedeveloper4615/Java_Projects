package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantUtf8 extends Constant {
    private  String value;

    public ConstantUtf8(DataInputStream dataInput) throws IOException {
        super(ClassFileConstants.CONSTANT_Utf8);
        value = dataInput.readUTF();
    }

    public String getBytes() {
        return value;
    }

    @Override
    public String toString() {
        return "ConstantUtf8(\"" + value + "\")";
    }
}
