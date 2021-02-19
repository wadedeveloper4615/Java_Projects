package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantInterfaceMethodRef extends ConstantConstantPool {
    public ConstantInterfaceMethodRef(DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, input);
    }

    public ConstantInterfaceMethodRef(int class_index, int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }
}
