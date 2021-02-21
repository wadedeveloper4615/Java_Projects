package com.wade.decompiler.classfile.instructions.base;

public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {
    Number getValue();
}
