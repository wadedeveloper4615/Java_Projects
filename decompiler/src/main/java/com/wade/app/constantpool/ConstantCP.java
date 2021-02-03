package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class ConstantCP extends Constant {
    protected int class_index;
    protected int name_and_type_index;

    public ConstantCP(final byte tag, final DataInputStream file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantCP(final byte tag, final int class_index, final int name_and_type_index) {
        super(tag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }

    public ConstantCP(final ConstantCP c) {
        this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
    }

    public int getClassIndex() {
        return class_index;
    }

    public int getNameAndTypeIndex() {
        return name_and_type_index;
    }

}
