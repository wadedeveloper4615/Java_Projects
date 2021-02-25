package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantModule extends Constant implements ConstantObject {
    private final int nameIndex;

    public ConstantModule(DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantModule(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Module);
        this.nameIndex = nameIndex;
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

    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ")";
    }
}
