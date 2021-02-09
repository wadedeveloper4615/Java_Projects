package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class MethodParameters extends Attribute {
    private MethodParameter[] parameters = new MethodParameter[0];

    public MethodParameters(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);

        int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input);
        }
    }

    @Override
    public String toString() {
        return "MethodParameters [parameters=" + Arrays.toString(parameters) + "]";
    }
}
