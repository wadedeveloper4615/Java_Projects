package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class NestHost extends Attribute {
    private int hostClassIndex;

    public NestHost(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, 0, constant_pool);
        hostClassIndex = input.readUnsignedShort();
    }

    public NestHost(int nameIndex, int length, int hostClassIndex, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.hostClassIndex = hostClassIndex;
    }

    public NestHost(NestHost c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    @Override
    public void accept(Visitor v) {
        v.visitNestHost(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        NestHost c = (NestHost) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(hostClassIndex);
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }

    public void setHostClassIndex(int hostClassIndex) {
        this.hostClassIndex = hostClassIndex;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("NestHost: ");
        String class_name = super.getConstantPool().getConstantString(hostClassIndex, ClassFileConstants.CONSTANT_Class);
        buf.append(Utility.compactClassName(class_name, false));
        return buf.toString();
    }
}
