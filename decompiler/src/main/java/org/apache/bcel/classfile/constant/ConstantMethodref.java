
package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantMethodref extends ConstantCP {

    public ConstantMethodref(final ConstantMethodref c) {
        super(ClassFileConstants.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public ConstantMethodref(final DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Methodref, input);
    }

    public ConstantMethodref(final int class_index, final int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Methodref, class_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantMethodref(this);
    }
}
