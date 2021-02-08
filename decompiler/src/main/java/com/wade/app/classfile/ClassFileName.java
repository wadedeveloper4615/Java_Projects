package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileConstants;

public class ClassFileName {
    private int nameIndex;
    private String name;

    public ClassFileName(DataInputStream in, ConstantPool constantPool) throws IOException {
        this.nameIndex = in.readUnsignedShort();
        this.name = constantPool.getConstantString(nameIndex, ClassFileConstants.CONSTANT_Class);
    }

    @Override
    public String toString() {
        return "ClassFileName [name=" + name + "]";
    }
}
