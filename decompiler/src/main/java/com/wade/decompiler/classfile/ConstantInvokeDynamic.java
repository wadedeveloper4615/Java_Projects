package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantInvokeDynamic extends ConstantCP {
    public ConstantInvokeDynamic(ConstantInvokeDynamic c) {
        this(c.getBootstrapMethodAttrIndex(), c.getNameAndTypeIndex());
    }

    ConstantInvokeDynamic(DataInput file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantInvokeDynamic(int bootstrap_method_attr_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InvokeDynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantInvokeDynamic(this);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex();
    }

    @Override
    public String toString() {
        return super.toString().replace("class_index", "bootstrap_method_attr_index");
    }
}
