package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;

public final class ConstantInvokeDynamic extends ConstantCP {
    public ConstantInvokeDynamic(final ConstantInvokeDynamic c) {
        this(c.getBootstrapMethodAttrIndex(), c.getNameAndTypeIndex());
    }

    public ConstantInvokeDynamic(final DataInputStream file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantInvokeDynamic(final int bootstrap_method_attr_index, final int name_and_type_index) {
        super(Const.CONSTANT_InvokeDynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex();
    }
}
