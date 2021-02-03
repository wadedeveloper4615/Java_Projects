package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;

public final class ConstantPackage extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantPackage(final ConstantPackage c) {
        this(c.getNameIndex());
    }

    ConstantPackage(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantPackage(final int nameIndex) {
        super(Const.CONSTANT_Package);
        this.nameIndex = nameIndex;
    }

    public String getBytes(final ConstantPool cp) throws ClassFormatException {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) throws ClassFormatException {
        final Constant c = cp.getConstant(nameIndex, Const.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
