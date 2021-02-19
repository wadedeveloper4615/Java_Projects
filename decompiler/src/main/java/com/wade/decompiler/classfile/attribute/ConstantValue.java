package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantDouble;
import com.wade.decompiler.classfile.constant.ConstantFloat;
import com.wade.decompiler.classfile.constant.ConstantInteger;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantString;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ConstantValue extends Attribute {
    private final int constantValueIndex;
    private final Constant constantValue;

    public ConstantValue(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    public ConstantValue(int name_index, int length, int constantValueIndex, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
        this.constantValue = constant_pool.getConstant(constantValueIndex);
    }

    public Constant getConstantValue() {
        return constantValue;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    @Override
    public String toString() {
        String buf = switch (constantValue.getTag()) {
            case CONSTANT_Long -> String.valueOf(((ConstantLong) constantValue).getBytes());
            case CONSTANT_Float -> String.valueOf(((ConstantFloat) constantValue).getBytes());
            case CONSTANT_Double -> String.valueOf(((ConstantDouble) constantValue).getBytes());
            case CONSTANT_Integer -> String.valueOf(((ConstantInteger) constantValue).getBytes());
            case CONSTANT_String -> {
                int i = ((ConstantString) constantValue).getStringIndex();
                Constant c = super.getConstantPool().getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield "\"" + Utility.convertString(((ConstantUtf8) c).getBytes()) + "\"";
            }
            default -> throw new IllegalStateException("Type of ConstValue invalid: " + constantValue);
        };
        return buf;
    }
}
