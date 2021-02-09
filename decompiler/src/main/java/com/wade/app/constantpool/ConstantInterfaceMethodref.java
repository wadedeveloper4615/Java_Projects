package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantInterfaceMethodRef extends ConstantCP {
    public ConstantInterfaceMethodRef(DataInputStream input) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, input);
    }

    @Override
    public String toString() {
        return "ConstantInterfaceMethodRef(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")\n";
    }
}
