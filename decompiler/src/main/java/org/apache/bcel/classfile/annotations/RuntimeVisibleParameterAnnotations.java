package org.apache.bcel.classfile.annotations;

import java.io.DataInput;
import java.io.IOException;

import org.apache.bcel.classfile.attribute.ParameterAnnotations;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;

public class RuntimeVisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeVisibleParameterAnnotations(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, name_index, length, input, constant_pool);
    }
}
