package com.wade.decompiler.generic;

public interface TypedInstruction {
    Type getType(ConstantPoolGen cpg);
}
