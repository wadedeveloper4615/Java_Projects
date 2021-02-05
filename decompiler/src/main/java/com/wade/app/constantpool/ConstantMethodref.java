package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantMethodRef extends ConstantCP {
    public ConstantMethodRef(final DataInputStream input) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, input);
    }

    @Override
    public String toString() {
        return "ConstantMethodRef(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
