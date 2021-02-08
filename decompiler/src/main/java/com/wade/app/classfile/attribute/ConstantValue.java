package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.app.constantpool.Constant;
import com.wade.app.constantpool.ConstantDouble;
import com.wade.app.constantpool.ConstantFloat;
import com.wade.app.constantpool.ConstantInteger;
import com.wade.app.constantpool.ConstantLong;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantString;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.enums.ClassFileAttributes;
import com.wade.app.enums.ClassFileConstants;

public class ConstantValue extends Attribute {
    private int constantValueIndex;

    public ConstantValue(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(int name_index, int length, int constantValueIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    public String convert() {
        Constant c = super.getConstant_pool().getConstant(constantValueIndex);
        String buf;
        int i;
        // Print constant to string depending on its type
        switch (c.getTag()) {
            case CONSTANT_Long:
                buf = String.valueOf(((ConstantLong) c).getBytes());
                break;
            case CONSTANT_Float:
                buf = String.valueOf(((ConstantFloat) c).getBytes());
                break;
            case CONSTANT_Double:
                buf = String.valueOf(((ConstantDouble) c).getBytes());
                break;
            case CONSTANT_Integer:
                buf = String.valueOf(((ConstantInteger) c).getBytes());
                break;
            case CONSTANT_String:
                i = ((ConstantString) c).getStringIndex();
                c = super.getConstant_pool().getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                buf = "\"" + ((ConstantUtf8) c).getBytes() + "\"";
                break;
            default:
                throw new IllegalStateException("Type of ConstValue invalid: " + c);
        }
        return buf;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    @Override
    public String toString() {
        return "ConstantValue [constantValueIndex=" + constantValueIndex + ", value=" + convert() + "]";
    }
}
