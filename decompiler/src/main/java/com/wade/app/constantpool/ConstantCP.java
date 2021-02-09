package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;

public class ConstantCP extends Constant {
    protected int class_index;
    protected int name_and_type_index;

    public ConstantCP(ClassFileConstants tag, DataInputStream file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    public ConstantCP(ClassFileConstants tag, int class_index, int name_and_type_index) {
        super(tag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }

    @Override
    public String toString() {
        return "ConstantCP [class_index=" + class_index + ", name_and_type_index=" + name_and_type_index + "]\n";
    }
}
