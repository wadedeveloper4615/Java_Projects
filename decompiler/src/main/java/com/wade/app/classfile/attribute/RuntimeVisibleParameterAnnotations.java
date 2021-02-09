package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class RuntimeVisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeVisibleParameterAnnotations(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }

    @Override
    public String toString() {
        return "RuntimeVisibleParameterAnnotations [" + super.toString() + "]";
    }
}
