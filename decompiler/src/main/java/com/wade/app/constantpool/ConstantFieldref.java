package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantFieldRef extends ConstantCP {
    public ConstantFieldRef(DataInputStream in) throws IOException {
        super(ClassFileConstants.CONSTANT_Fieldref, in);
    }

    @Override
    public String toString() {
        return "ConstantFieldRef(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
