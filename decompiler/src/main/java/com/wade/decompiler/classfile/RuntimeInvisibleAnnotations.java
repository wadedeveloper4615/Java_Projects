package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class RuntimeInvisibleAnnotations extends Annotations {
    public RuntimeInvisibleAnnotations(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_INVISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, false);
    }

    @Override
    public Attribute copy(ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        super.dump(dos);
        writeAnnotations(dos);
    }
}
