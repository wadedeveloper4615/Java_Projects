
package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantInterfaceMethodref extends ConstantCP {

    public ConstantInterfaceMethodref(final ConstantInterfaceMethodref c) {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public ConstantInterfaceMethodref(final DataInput input) throws IOException {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, input);
    }

    public ConstantInterfaceMethodref(final int class_index, final int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantInterfaceMethodref(this);
    }
}
