package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.ClassFormatException;
import com.wade.app.enums.ClassFileConstants;

public class ConstantPool {
    private int constant_pool_count;
    private Constant[] constantPool;

    public ConstantPool(DataInputStream in) throws IOException {
        constant_pool_count = in.readUnsignedShort();
        constantPool = new Constant[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            constantPool[i] = Constant.readConstant(in);
            ClassFileConstants tag = constantPool[i].getTag();
            if ((tag == ClassFileConstants.CONSTANT_Double) || (tag == ClassFileConstants.CONSTANT_Long)) {
                i++;
            }
        }
    }

    public Constant getConstant(int index) {
        if (index >= constantPool.length || index < 0) {
            throw new ClassFormatException("Invalid constant pool reference: " + index + ". Constant pool size is: " + constantPool.length);
        }
        return constantPool[index];
    }

    public Constant getConstant(int index, ClassFileConstants tag) throws ClassFormatException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag() != tag) {
            throw new ClassFormatException("Expected class `" + tag.getName() + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public String getConstantString(int index, ClassFileConstants tag) throws ClassFormatException {
        int i;
        Constant c = getConstant(index, tag);
        i = switch (tag) {
            case CONSTANT_Class -> ((ConstantClass) c).getNameIndex();
            case CONSTANT_String -> ((ConstantString) c).getStringIndex();
            case CONSTANT_Module -> ((ConstantModule) c).getNameIndex();
            case CONSTANT_Package -> ((ConstantPackage) c).getNameIndex();
            default -> throw new IllegalArgumentException("getConstantString called with illegal tag " + tag);
        };
        c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    @Override
    public String toString() {
        return "ConstantPool [constant_pool_count=" + constant_pool_count + ", constantPool=\n" + Arrays.toString(constantPool) + "]";
    }
}
