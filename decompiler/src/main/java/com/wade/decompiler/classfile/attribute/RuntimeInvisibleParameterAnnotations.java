package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class RuntimeInvisibleParameterAnnotations extends ParameterAnnotations {
    public RuntimeInvisibleParameterAnnotations(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, nameIndex, length, input, constantPool);
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
