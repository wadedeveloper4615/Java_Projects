package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public abstract class ConstantConstantPool extends Constant {
    protected final int classIndex;
    protected final int nameAndTypeIndex;

    public ConstantConstantPool(ClassFileConstants tag, DataInputStream file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantConstantPool(ClassFileConstants tag, int classIndex, int nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public String getClass(ConstantPool cp) {
        return cp.constantToString(classIndex, ClassFileConstants.CONSTANT_Class);
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(class_index = " + classIndex + ", name_and_type_index = " + nameAndTypeIndex + ")";
    }
}
