package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class RuntimeVisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeVisibleParameterAnnotations(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }
}
