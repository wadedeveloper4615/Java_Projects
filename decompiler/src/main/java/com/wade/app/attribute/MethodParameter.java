package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.AccessFlags;
import com.wade.app.ClassFileConstants;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.exception.ClassFormatException;

public class MethodParameter {
    private int nameIndex;
    private AccessFlags accessFlags;

    public MethodParameter() {
    }

    public MethodParameter(final DataInputStream input) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = AccessFlags.read(input);
    }

    public AccessFlags getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getParameterName(final ConstantPool constant_pool) throws ClassFormatException {
        if (nameIndex == 0) {
            return null;
        }
        return ((ConstantUtf8) constant_pool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public boolean isFinal() {
        return accessFlags.isFinal();
    }

    public boolean isMandated() {
        return accessFlags.isMandated();
    }

    public boolean isSynthetic() {
        return accessFlags.isSynthetic();
    }

    public void setAccessFlags(AccessFlags access_flags) {
        this.accessFlags = access_flags;
    }
}
