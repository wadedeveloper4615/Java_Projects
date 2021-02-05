package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;
import com.wade.app.exception.ClassFormatException;

public final class ConstantModule extends Constant implements ConstantObject {
    private int nameIndex;

    public ConstantModule(final ConstantModule c) {
        this(c.getNameIndex());
    }

    public ConstantModule(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantModule(final int nameIndex) {
        super(ClassFileConstants.CONSTANT_Module);
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
