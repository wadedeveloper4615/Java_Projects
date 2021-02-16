package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public final class ConstantClass extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantClass(ConstantClass c) {
        this(c.getNameIndex());
    }

    public ConstantClass(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    public ConstantClass(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantClass(this);
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
        final Constant c = cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8.getTag());
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
