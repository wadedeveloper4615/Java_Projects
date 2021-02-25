package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantString extends Constant implements ConstantObject {
    private final int stringIndex;

    public ConstantString(DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    public ConstantString(int stringIndex) {
        super(ClassFileConstants.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    public String getBytes(ConstantPool cp) {
        return (String) getConstantValue(cp);
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        Constant c = cp.getConstant(stringIndex, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getStringIndex() {
        return stringIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "(stringIndex = " + stringIndex + ")";
    }
}
