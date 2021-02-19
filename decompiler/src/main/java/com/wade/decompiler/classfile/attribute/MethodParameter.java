package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassFileConstants;

public class MethodParameter {
    private int nameIndex;
    private int accessFlags;

    public MethodParameter() {
    }

    public MethodParameter(DataInput input) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = input.readUnsignedShort();
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getParameterName(ConstantPool constant_pool) {
        if (nameIndex == 0) {
            return null;
        }
        return ((ConstantUtf8) constant_pool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public boolean is() {
        return (accessFlags & ClassAccessFlags.ACC_FINAL.getFlag()) != 0;
    }

    public boolean isMandated() {
        return (accessFlags & ClassAccessFlags.ACC_MANDATED.getFlag()) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & ClassAccessFlags.ACC_SYNTHETIC.getFlag()) != 0;
    }

    public void setAccessFlags(int access_flags) {
        this.accessFlags = access_flags;
    }

    public void setNameIndex(int name_index) {
        this.nameIndex = name_index;
    }
}
