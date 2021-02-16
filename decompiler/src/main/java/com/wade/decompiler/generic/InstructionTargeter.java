package com.wade.decompiler.generic;

public interface InstructionTargeter {
    boolean containsTarget(InstructionHandle ih);

    void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) throws ClassGenException;
}
