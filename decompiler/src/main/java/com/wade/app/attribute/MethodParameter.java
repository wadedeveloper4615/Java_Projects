package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.exception.ClassFormatException;

public class MethodParameter {
    private int nameIndex;
    private int accessFlags;

    public MethodParameter() {
    }

    public MethodParameter(final DataInputStream input) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = input.readUnsignedShort();
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getParameterName(final ConstantPool constant_pool) throws ClassFormatException {
        if (nameIndex == 0) {
            return null;
        }
        return ((ConstantUtf8) constant_pool.getConstant(nameIndex, Const.CONSTANT_Utf8)).getBytes();
    }

    public boolean isFinal() {
        return (accessFlags & Const.ACC_FINAL) != 0;
    }

    public boolean isMandated() {
        return (accessFlags & Const.ACC_MANDATED) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & Const.ACC_SYNTHETIC) != 0;
    }

    public void setAccessFlags(final int access_flags) {
        this.accessFlags = access_flags;
    }
}
