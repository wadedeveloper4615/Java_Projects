package com.wade.app.constantpool;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public final class ConstantDynamic extends ConstantCP {
    public ConstantDynamic(final DataInput file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantDynamic(final int bootstrap_method_attr_index, final int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Dynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public String toString() {
        return "ConstantDynamic(bootstrap_method_attr_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
