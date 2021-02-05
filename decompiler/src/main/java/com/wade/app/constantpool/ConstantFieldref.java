package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public class ConstantFieldref extends ConstantCP {
    public ConstantFieldref(DataInputStream input) throws IOException {
        super(ClassFileConstants.CONSTANT_Fieldref, input);
    }

    public ConstantFieldref(final int class_index, final int name_and_type_index) throws IOException {
        super(ClassFileConstants.CONSTANT_Fieldref, class_index, name_and_type_index);
    }
}
