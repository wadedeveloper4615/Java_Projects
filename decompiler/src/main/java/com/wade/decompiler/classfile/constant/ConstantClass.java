package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantClass extends Constant implements ConstantObject {
    private final int nameIndex;

    public ConstantClass(DataInput dataInput) throws IOException {
        this(dataInput.readUnsignedShort());
    }

    private ConstantClass(int nameIndex) {
        super(ClassFileConstants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    @Override
    public Object getConstantValue(ConstantPool cp) {
        String name = ((ConstantUtf8) cp.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        return name;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    @Override
    public String toString() {
        return super.toString() + "[nameIndex=" + nameIndex + "]";
    }
}
