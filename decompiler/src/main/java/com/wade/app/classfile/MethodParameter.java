package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodParameter implements Cloneable {
    private int nameIndex;
    private int accessFlags;

    public MethodParameter(DataInputStream input) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = input.readUnsignedShort();
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
