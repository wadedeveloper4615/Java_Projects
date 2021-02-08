package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;

public class FieldVariable extends FieldOrMethod {
    public FieldVariable(DataInputStream in, ConstantPool constantPool) throws IOException {
        super(in, constantPool);
    }

    public FieldVariable(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constant_pool) {
        super(accessFlags, nameIndex, signatureIndex, attributes, constant_pool);
    }
}
