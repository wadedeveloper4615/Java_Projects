package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantDynamic extends ConstantCP {
    public ConstantDynamic(ConstantDynamic c) {
        this(c.getBootstrapMethodAttrIndex(), c.getNameAndTypeIndex());
    }

    ConstantDynamic(DataInput file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantDynamic(int bootstrap_method_attr_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Dynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantDynamic(this);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex(); // AKA bootstrap_method_attr_index
    }

    @Override
    public String toString() {
        return super.toString().replace("class_index", "bootstrap_method_attr_index");
    }
}
