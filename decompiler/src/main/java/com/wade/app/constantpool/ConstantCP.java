package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public abstract class ConstantCP extends Constant {
    protected int class_index;
    protected int name_and_type_index;

    public ConstantCP(final ClassFileConstants tag, final DataInputStream file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantCP(final ClassFileConstants tag, final int class_index, final int name_and_type_index) throws IOException {
        super(tag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }

    public ConstantCP(final ConstantCP c) throws IOException {
        this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public int getClassIndex() {
        return class_index;
    }

    public int getNameAndTypeIndex() {
        return name_and_type_index;
    }

}
