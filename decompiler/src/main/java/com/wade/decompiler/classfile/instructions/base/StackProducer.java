package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;

public interface StackProducer {
    int produceStack(ConstantPool cpg);
}
