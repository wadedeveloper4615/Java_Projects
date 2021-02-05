package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public class ConstantInterfaceMethodref extends ConstantCP {
    public ConstantInterfaceMethodref(final ConstantInterfaceMethodref c) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public ConstantInterfaceMethodref(final DataInputStream input) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, input);
    }

    public ConstantInterfaceMethodref(final int class_index, final int name_and_type_index) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }
}
