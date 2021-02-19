package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ConstantUtf8 extends Constant {
    private final String value;

    public ConstantUtf8(DataInput dataInput) throws IOException {
        super(ClassFileConstants.CONSTANT_Utf8);
        this.value = dataInput.readUTF();
    }

    public String getBytes() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString() + "(\"" + Utility.replace(value, "\n", "\\n") + "\")";
    }
}
