package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantMethodref extends ConstantCP {
    public ConstantMethodref(ConstantMethodref c) {
        super(ClassFileConstants.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantMethodref(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, input);
    }

    public ConstantMethodref(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Methodref, class_index, name_and_type_index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantMethodref(this);
    }
}
