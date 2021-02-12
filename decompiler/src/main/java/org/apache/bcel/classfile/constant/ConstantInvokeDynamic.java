package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantInvokeDynamic extends ConstantCP {
    public ConstantInvokeDynamic(final ConstantInvokeDynamic c) {
        this(c.getBootstrapMethodAttrIndex(), c.getNameAndTypeIndex());
    }

    public ConstantInvokeDynamic(final DataInput file) throws IOException {
        this(file.readShort(), file.readShort());
    }

    public ConstantInvokeDynamic(final int bootstrap_method_attr_index, final int name_and_type_index) {
        super(ClassFileConstants.CONSTANT_InvokeDynamic, bootstrap_method_attr_index, name_and_type_index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantInvokeDynamic(this);
    }

    public int getBootstrapMethodAttrIndex() {
        return super.getClassIndex();
    }

    @Override
    public String toString() {
        return super.toString().replace("class_index", "bootstrap_method_attr_index");
    }
}
