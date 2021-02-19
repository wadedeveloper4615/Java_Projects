package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class NestHost extends Attribute {
    private int hostClassIndex;
    private String hostClassName;

    public NestHost(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, 0, constant_pool);
        hostClassIndex = input.readUnsignedShort();
        hostClassName = constantPool.getConstantString(hostClassIndex, ClassFileConstants.CONSTANT_Class);
    }

    public NestHost(int nameIndex, int length, int hostClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.hostClassIndex = hostClassIndex;
        hostClassName = constantPool.getConstantString(hostClassIndex, ClassFileConstants.CONSTANT_Class);
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }

    public String getHostClassName() {
        return hostClassName;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("NestHost: ");
        buf.append(Utility.compactClassName(hostClassName, false));
        return buf.toString();
    }
}
