package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class RuntimeInvisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeInvisibleParameterAnnotations( int name_index,  int length,  DataInput input,  ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }
}
