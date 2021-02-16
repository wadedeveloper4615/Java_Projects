package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public final class ConstantString extends Constant implements ConstantObject {
    private int stringIndex; // Identical to ConstantClass except for this name

    public ConstantString(final ConstantString c) {
        this(c.getStringIndex());
    }

    ConstantString(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantString(final int stringIndex) {
        super(ClassFileConstants.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantString(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(stringIndex);
    }

    public String getBytes(final ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        final Constant c = cp.getConstant(stringIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(final int stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(stringIndex = " + stringIndex + ")";
    }
}
