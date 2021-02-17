package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class RuntimeVisibleAnnotations extends Annotations {
    public RuntimeVisibleAnnotations(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, true);
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
