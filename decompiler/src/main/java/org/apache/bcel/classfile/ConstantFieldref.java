
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantFieldref extends ConstantCP {

    public ConstantFieldref(final ConstantFieldref c) {
        super(ClassFileConstants.CONSTANT_Fieldref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantFieldref(final DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_Fieldref, input);
    }

    public ConstantFieldref(final int class_index, final int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_Fieldref, class_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantFieldref(this);
    }
}
