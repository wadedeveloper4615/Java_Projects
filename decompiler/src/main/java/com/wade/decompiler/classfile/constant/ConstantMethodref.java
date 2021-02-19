package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantMethodref extends ConstantConstantPool {
    public ConstantMethodref(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, input);
    }

    public ConstantMethodref(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Methodref, class_index, name_and_type_index);
    }
}
