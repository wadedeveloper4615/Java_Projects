package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantFieldref extends ConstantCP {
    public ConstantFieldref(ConstantFieldref c) {
        super(ClassFileConstants.CONSTANT_Fieldref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantFieldref(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Fieldref, input);
    }

    public ConstantFieldref(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Fieldref, class_index, name_and_type_index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantFieldref(this);
    }
}
