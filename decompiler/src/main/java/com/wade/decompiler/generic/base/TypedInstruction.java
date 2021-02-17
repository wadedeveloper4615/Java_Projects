package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;

public interface TypedInstruction {
    Type getType(ConstantPoolGen cpg);
}
