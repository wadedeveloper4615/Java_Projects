package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantInterfaceMethodref extends ConstantCP {
    public ConstantInterfaceMethodref(ConstantInterfaceMethodref c) {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantInterfaceMethodref(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, input);
    }

    public ConstantInterfaceMethodref(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }
}
