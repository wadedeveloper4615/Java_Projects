package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ClassGenException;

public interface InstructionTargeter {
    boolean containsTarget(InstructionHandle ih);

    void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) throws ClassGenException;
}
