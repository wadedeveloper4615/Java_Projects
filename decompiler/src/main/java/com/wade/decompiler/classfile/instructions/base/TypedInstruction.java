package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.instructions.type.Type;

public interface TypedInstruction {
    Type getType();
}
