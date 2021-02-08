package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public  class NestHost extends Attribute {
    private int hostClassIndex;

    public NestHost( int name_index,  int length,  DataInput input,  ConstantPool constant_pool) throws IOException {
        this(name_index, length, 0, constant_pool);
        hostClassIndex = input.readUnsignedShort();
    }

    public NestHost( int nameIndex,  int length,  int hostClassIndex,  ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.hostClassIndex = hostClassIndex;
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }
}
