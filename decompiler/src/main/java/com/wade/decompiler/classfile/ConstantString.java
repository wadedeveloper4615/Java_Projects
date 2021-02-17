package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantString extends Constant implements ConstantObject {
    private int stringIndex; // Identical to ConstantClass except for this name

    public ConstantString(ConstantString c) {
        this(c.getStringIndex());
    }

    ConstantString(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantString(int stringIndex) {
        super(ClassFileConstants.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantString(this);
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(stringIndex);
    }

    public String getBytes(ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        Constant c = cp.getConstant(stringIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(stringIndex = " + stringIndex + ")";
    }
}
