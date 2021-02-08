package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.constantpool.ConstantPool;

public class MethodCode extends FieldOrMethod {
    public MethodCode(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public MethodCode(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) throws IOException {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
