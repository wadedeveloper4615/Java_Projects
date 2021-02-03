package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class RuntimeVisibleAnnotations extends Annotations {
    public RuntimeVisibleAnnotations(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_RUNTIME_VISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, true);
    }
}
