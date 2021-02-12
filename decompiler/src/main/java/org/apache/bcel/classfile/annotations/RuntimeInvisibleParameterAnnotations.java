package org.apache.bcel.classfile.annotations;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.classfile.attribute.ParameterAnnotations;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;

public class RuntimeInvisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeInvisibleParameterAnnotations(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }
}
