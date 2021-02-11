
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ConstantMethodType extends Constant {

    private int descriptorIndex;

    public ConstantMethodType(final ConstantMethodType c) {
        this(c.getDescriptorIndex());
    }

    ConstantMethodType(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantMethodType(final int descriptor_index) {
        super(Const.CONSTANT_MethodType);
        this.descriptorIndex = descriptor_index;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantMethodType(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(descriptorIndex);
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(final int descriptor_index) {
        this.descriptorIndex = descriptor_index;
    }

    @Override
    public String toString() {
        return super.toString() + "(descriptorIndex = " + descriptorIndex + ")";
    }
}
