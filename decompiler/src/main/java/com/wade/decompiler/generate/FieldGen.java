package com.wade.decompiler.generate;

import java.io.IOException;

import com.wade.decompiler.classfile.FieldOrMethod;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class FieldGen extends FieldOrMethodGen {
    public FieldGen(FieldOrMethod value, ConstantPool constantPool) throws IOException {
        super(value, constantPool);
    }
}
