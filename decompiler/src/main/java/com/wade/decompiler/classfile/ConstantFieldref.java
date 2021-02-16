package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.Const;

public final class ConstantFieldref extends ConstantCP {
    public ConstantFieldref(final ConstantFieldref c) {
        super(Const.CONSTANT_Fieldref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantFieldref(final DataInput input) throws IOException {
        super(Const.CONSTANT_Fieldref, input);
    }

    public ConstantFieldref(final int class_index, final int name_and_type_index) {
        super(Const.CONSTANT_Fieldref, class_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantFieldref(this);
    }
}
