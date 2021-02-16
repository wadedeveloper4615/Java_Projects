package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.Const;

public final class ConstantInterfaceMethodref extends ConstantCP {
    public ConstantInterfaceMethodref(final ConstantInterfaceMethodref c) {
        super(Const.CONSTANT_InterfaceMethodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantInterfaceMethodref(final DataInput input) throws IOException {
        super(Const.CONSTANT_InterfaceMethodref, input);
    }

    public ConstantInterfaceMethodref(final int class_index, final int name_and_type_index) {
        super(Const.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantInterfaceMethodref(this);
    }
}