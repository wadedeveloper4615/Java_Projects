package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class RuntimeVisibleAnnotations extends Annotations {
    public RuntimeVisibleAnnotations(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_ANNOTATIONS, nameIndex, length, input, constantPool, true);
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
