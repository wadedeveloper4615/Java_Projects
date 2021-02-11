
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ConstantMethodref extends ConstantCP {

    public ConstantMethodref(final ConstantMethodref c) {
        super(Const.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantMethodref(final DataInput input) throws IOException {
        super(Const.CONSTANT_Methodref, input);
    }

    public ConstantMethodref(final int class_index, final int name_and_type_index) {
        super(Const.CONSTANT_Methodref, class_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantMethodref(this);
    }
}
