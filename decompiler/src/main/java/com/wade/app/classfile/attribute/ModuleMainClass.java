package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public  class ModuleMainClass extends Attribute {
    private int mainClassIndex;

    public ModuleMainClass( int nameIndex,  int length,  DataInputStream input,  ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
    }

    public ModuleMainClass( int name_index,  int length,  int mainClassIndex,  ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, name_index, length, constantPool);
        this.mainClassIndex = mainClassIndex;
    }

    public int getMainClassIndex() {
        return mainClassIndex;
    }
}
