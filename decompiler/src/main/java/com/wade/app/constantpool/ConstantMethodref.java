package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;

public class ConstantMethodref extends ConstantCP {
    public ConstantMethodref(final ConstantMethodref c) {
        super(Const.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public ConstantMethodref(final DataInputStream input) throws IOException {
        super(Const.CONSTANT_Methodref, input);
    }

    public ConstantMethodref(final int class_index, final int name_and_type_index) {
        super(Const.CONSTANT_Methodref, class_index, name_and_type_index);
    }
}
