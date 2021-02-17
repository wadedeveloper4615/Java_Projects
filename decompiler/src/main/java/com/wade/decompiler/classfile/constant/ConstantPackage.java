package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantPackage extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantPackage(ConstantPackage c) {
        this(c.getNameIndex());
    }

    ConstantPackage(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantPackage(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Package);
        this.nameIndex = nameIndex;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantPackage(this);
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeShort(nameIndex);
    }

    public String getBytes(ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        Constant c = cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ")";
    }
}
