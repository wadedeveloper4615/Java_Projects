package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public abstract class ConstantCP extends Constant {
    protected int class_index;
    protected int name_and_type_index;

    public ConstantCP(byte tag, final DataInput file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantCP(byte tag, final int class_index, final int name_and_type_index) {
        super(tag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }

    public ConstantCP(final ConstantCP c) {
        this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(class_index);
        file.writeShort(name_and_type_index);
    }

    public String getClass(final ConstantPool cp) {
        return cp.constantToString(class_index, Const.CONSTANT_Class);
    }

    public final int getClassIndex() {
        return class_index;
    }

    public final int getNameAndTypeIndex() {
        return name_and_type_index;
    }

    public final void setClassIndex(final int class_index) {
        this.class_index = class_index;
    }

    public final void setNameAndTypeIndex(final int name_and_type_index) {
        this.name_and_type_index = name_and_type_index;
    }

    @Override
    public String toString() {
        return super.toString() + "(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
