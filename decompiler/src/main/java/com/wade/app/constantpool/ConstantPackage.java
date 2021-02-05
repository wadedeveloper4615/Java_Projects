package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;
import com.wade.app.exception.ClassFormatException;

public final class ConstantPackage extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantPackage(final ConstantPackage c) {
        this(c.getNameIndex());
    }

    ConstantPackage(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantPackage(final int nameIndex) {
        super(ClassFileConstants.CONSTANT_Package);
        this.nameIndex = nameIndex;
    }

    public String getBytes(final ConstantPool cp) throws ClassFormatException {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) throws ClassFormatException {
        final Constant c = cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
