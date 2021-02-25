package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class RuntimeVisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeVisibleParameterAnnotations(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
