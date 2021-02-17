package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;

public interface StackProducer {
    int produceStack(ConstantPoolGen cpg);
}
