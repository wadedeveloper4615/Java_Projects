package com.wade.decompiler.generate;

import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class MethodGen extends FieldOrMethodGen {
    public MethodGen(Method value, ConstantPool constantPool) {
        super(value, constantPool);
    }
}
