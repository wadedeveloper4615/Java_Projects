package com.wade.decompiler.classfile.instructions.base;

public interface InstructionTargeter {
    boolean containsTarget(InstructionHandle ih);

    void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) throws ClassGenException;
}
