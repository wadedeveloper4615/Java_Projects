package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;

public interface StackConsumer {
    int consumeStack(ConstantPool cpg);
}
