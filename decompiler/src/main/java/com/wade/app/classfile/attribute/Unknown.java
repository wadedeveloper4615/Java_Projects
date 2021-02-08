package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.enums.ClassFileAttributes;
import com.wade.app.enums.ClassFileConstants;

public  class Unknown extends Attribute {
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

    public String getName() {
        return name;
    }
}
