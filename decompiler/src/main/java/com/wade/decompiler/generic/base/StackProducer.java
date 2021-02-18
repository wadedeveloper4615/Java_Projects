package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;

public interface StackProducer {
    int produceStack(ConstantPool cpg);
}
