package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodParameter {
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

    @Override
    public String toString() {
        return "MethodParameter [nameIndex=" + nameIndex + ", accessFlags=" + accessFlags + "]";
    }
}
