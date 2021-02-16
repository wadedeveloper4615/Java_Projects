package com.wade.decompiler.generic;

public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {
    Number getValue();
}
