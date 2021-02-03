package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;

public final class ConstantString extends Constant implements ConstantObject {
    private int stringIndex;

    public ConstantString(final ConstantString c) {
        this(c.getStringIndex());
    }

    public ConstantString(final DataInputStream file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantString(final int stringIndex) {
        super(Const.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) throws ClassFormatException {
        final Constant c = cp.getConstant(stringIndex, Const.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getStringIndex() {
        return stringIndex;
    }
}
