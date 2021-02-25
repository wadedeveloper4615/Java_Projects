package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class EnclosingMethod extends Attribute {
    private int classIndex;
    private int methodIndex;

    public EnclosingMethod(int nameIndex, int len, DataInputStream input, ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    private EnclosingMethod(int nameIndex, int len, int classIdx, int methodIdx, ConstantPool cpool) {
        super(ClassFileAttributes.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        this.classIndex = classIdx;
        this.methodIndex = methodIdx;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnclosingMethod other = (EnclosingMethod) obj;
        if (classIndex != other.classIndex)
            return false;
        if (methodIndex != other.methodIndex)
            return false;
        return true;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + classIndex;
        result = prime * result + methodIndex;
        return result;
    }

    @Override
    public String toString() {
        return "EnclosingMethod [classIndex=" + classIndex + ", methodIndex=" + methodIndex + "]";
    }
}
