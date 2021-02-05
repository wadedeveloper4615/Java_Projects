package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public class ConstantMethodref extends ConstantCP {
    public ConstantMethodref(final ConstantMethodref c) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public ConstantMethodref(final DataInputStream input) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, input);
    }

    public ConstantMethodref(final int class_index, final int name_and_type_index) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, class_index, name_and_type_index);
    }
}
