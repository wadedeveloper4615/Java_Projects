package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.ConstantObject;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.enums.ClassFileConstants;

public final class ConstantModule extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantModule(final ConstantModule c) {
        this(c.getNameIndex());
    }

    public ConstantModule(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantModule(final int nameIndex) {
        super(ClassFileConstants.CONSTANT_Module);
        this.nameIndex = nameIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantModule(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(nameIndex);
    }

    public String getBytes(final ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        final Constant c = cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ")";
    }
}
