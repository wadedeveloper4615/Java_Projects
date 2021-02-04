package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class MethodParameters extends Attribute {
    private MethodParameter[] parameters = new MethodParameter[0];

    public MethodParameters(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);

        final int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input);
        }
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }
}
