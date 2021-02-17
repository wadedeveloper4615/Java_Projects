package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;

public interface StackConsumer {
    int consumeStack(ConstantPoolGen cpg);
}
