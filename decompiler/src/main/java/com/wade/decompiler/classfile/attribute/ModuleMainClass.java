package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class ModuleMainClass extends Attribute {
    private int mainClassIndex;

    public ModuleMainClass(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, 0, constantPool);
        mainClassIndex = input.readUnsignedShort();
    }

    public ModuleMainClass(int nameIndex, int length, int mainClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.mainClassIndex = mainClassIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleMainClass other = (ModuleMainClass) obj;
        if (mainClassIndex != other.mainClassIndex)
            return false;
        return true;
    }

    public int getMainClassIndex() {
        return mainClassIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + mainClassIndex;
        return result;
    }

    @Override
    public String toString() {
        return "ModuleMainClass [mainClassIndex=" + mainClassIndex + "]";
    }
}
