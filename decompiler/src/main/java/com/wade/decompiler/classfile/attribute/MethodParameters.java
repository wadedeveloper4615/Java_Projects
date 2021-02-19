package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class MethodParameters extends Attribute {
    private MethodParameter[] parameters = new MethodParameter[0];

    public MethodParameters(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);
        int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input);
        }
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(MethodParameter[] parameters) {
        this.parameters = parameters;
    }
}
