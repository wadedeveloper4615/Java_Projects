package org.apache.bcel;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;

public class ClassFileName {
    private int nameIndex;
    private String name;

    public ClassFileName(DataInputStream in, ConstantPool constantPool) throws IOException {
        this.nameIndex = in.readUnsignedShort();
        this.name = constantPool.getConstantString(nameIndex, ClassFileConstants.CONSTANT_Class);
    }

    public ClassFileName(String name, int nameIndex) {
        this.nameIndex = nameIndex;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return "ClassFileName [nameIndex=" + nameIndex + ", name=" + name + "]";
    }
}
