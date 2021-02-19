package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class Unknown extends Attribute {
    private byte[] bytes;
    private String name;

    public Unknown(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_UNKNOWN, name_index, length, constant_pool);
        this.bytes = bytes;
        this.name = ((ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public Unknown(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        if (super.getLength() == 0 || bytes == null) {
            return "(Unknown attribute " + name + ")";
        }
        String hex;
        if (super.getLength() > 10) {
            byte[] tmp = new byte[10];
            System.arraycopy(bytes, 0, tmp, 0, 10);
            hex = Utility.toHexString(tmp) + "... (truncated)";
        } else {
            hex = Utility.toHexString(bytes);
        }
        return "(Unknown attribute " + name + ": " + hex + ")";
    }
}
