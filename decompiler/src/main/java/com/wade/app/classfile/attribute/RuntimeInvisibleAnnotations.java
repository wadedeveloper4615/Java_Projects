package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class RuntimeInvisibleAnnotations extends Annotations {
    public RuntimeInvisibleAnnotations(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_INVISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, false);
    }

    @Override
    public String toString() {
        return "RuntimeInvisibleAnnotations [getAnnotationTable()=" + Arrays.toString(getAnnotationTable()) + ", isRuntimeVisible()=" + isRuntimeVisible() + ", getConstant_pool()=" + getConstant_pool() + ", getLength()=" + getLength() + ", getName_index()=" + getName_index() + ", getTag()=" + getTag() + "]";
    }
}
