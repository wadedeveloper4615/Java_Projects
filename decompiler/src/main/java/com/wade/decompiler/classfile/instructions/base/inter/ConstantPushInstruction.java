package com.wade.decompiler.classfile.instructions.base.inter;

public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {
    Number getValue();
}
