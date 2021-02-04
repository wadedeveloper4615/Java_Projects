package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class RuntimeInvisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeInvisibleParameterAnnotations(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }
}
