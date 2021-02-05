package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantInvokeDynamic extends ConstantCP {
    ConstantInvokeDynamic(final DataInput file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantInvokeDynamic(final int bootstrap_method_attr_index, final int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InvokeDynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public String toString() {
        return "ConstantInvokeDynamic(bootstrap_method_attr_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
