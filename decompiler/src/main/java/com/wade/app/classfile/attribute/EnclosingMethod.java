package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class EnclosingMethod extends Attribute {
    private int classIndex;
    private int methodIndex;

    public EnclosingMethod(int nameIndex, int len, DataInputStream input, ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    public EnclosingMethod(int nameIndex, int len, int classIdx, int methodIdx, ConstantPool cpool) {
        super(ClassFileAttributes.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        classIndex = classIdx;
        methodIndex = methodIdx;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    @Override
    public String toString() {
        return "EnclosingMethod [classIndex=" + classIndex + ", methodIndex=" + methodIndex + "]";
    }

}
