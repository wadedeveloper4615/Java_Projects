package org.apache.bcel.generic.base;

import org.apache.bcel.generic.gen.ConstantPoolGen;

public interface StackConsumer {
    int consumeStack(ConstantPoolGen cpg);
}
