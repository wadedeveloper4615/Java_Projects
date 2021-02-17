package com.wade.decompiler.generic.base;

public interface ConstantPushInstruction extends PushInstruction, TypedInstruction {
    Number getValue();
}
