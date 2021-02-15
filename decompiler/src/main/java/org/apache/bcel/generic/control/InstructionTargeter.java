package org.apache.bcel.generic.control;

import org.apache.bcel.generic.base.ClassGenException;

public interface InstructionTargeter {
    boolean containsTarget(InstructionHandle ih);

    void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) throws ClassGenException;
}
