package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantFieldRef extends ConstantConstantPool {
    public ConstantFieldRef(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Fieldref, input);
    }

    public ConstantFieldRef(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Fieldref, class_index, name_and_type_index);
    }
}