package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public abstract class ConstantCP extends Constant {
    protected int class_index;
    protected int name_and_type_index;

    public ConstantCP(ClassFileConstants tag, DataInput file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantCP(ClassFileConstants tag, int class_index, int name_and_type_index) {
        super(tag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }

    public ConstantCP(ConstantCP c) {
        this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(class_index);
        file.writeShort(name_and_type_index);
    }

    public String getClass(ConstantPool cp) {
        return cp.constantToString(class_index, ClassFileConstants.CONSTANT_Class);
    }

    public int getClassIndex() {
        return class_index;
    }

    public int getNameAndTypeIndex() {
        return name_and_type_index;
    }

    public void setClassIndex(int class_index) {
        this.class_index = class_index;
    }

    public void setNameAndTypeIndex(int name_and_type_index) {
        this.name_and_type_index = name_and_type_index;
    }

    @Override
    public String toString() {
        return super.toString() + "(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
