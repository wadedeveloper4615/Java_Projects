package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.type.Type;

public interface TypedInstruction {
    Type getType(ConstantPoolGen cpg);
}
