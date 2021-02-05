package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;

public final class ConstantDynamic extends ConstantCP {
    public ConstantDynamic(final ConstantDynamic c) throws IOException {
        this(c.getBootstrapMethodAttrIndex(), c.getNameAndTypeIndex());
    }

    public ConstantDynamic(final DataInputStream file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantDynamic(final int bootstrap_method_attr_index, final int name_and_type_index) throws IOException {
        super(ClassFileConstants.CONSTANT_Dynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex();
    }
}
