package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;

public class ConstantPool {
    private Constant[] constantPool;

    public ConstantPool(DataInputStream input) throws IOException, ClassFormatException {
        byte tag;
        final int constant_pool_count = input.readUnsignedShort();
        constantPool = new Constant[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            constantPool[i] = Constant.readConstant(input);
            tag = constantPool[i].getTag();
            if ((tag == Const.CONSTANT_Double) || (tag == Const.CONSTANT_Long)) {
                i++;
            }
        }
    }

    public Constant getConstant(final int index) throws ClassFormatException {
        if (index >= constantPool.length || index < 0) {
            throw new ClassFormatException("Invalid constant pool reference: " + index + ". Constant pool size is: " + constantPool.length);
        }
        return constantPool[index];
    }

    public Constant getConstant(final int index, final byte tag) throws ClassFormatException {
        Constant c;
        c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag() != tag) {
            throw new ClassFormatException("Expected class `" + Const.getConstantName(tag) + "' at index " + index + " and got " + c);
        }
        return c;
    }
}
