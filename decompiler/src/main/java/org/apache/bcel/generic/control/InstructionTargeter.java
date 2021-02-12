package org.apache.bcel.generic.control;

import org.apache.bcel.generic.base.BranchHandle;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.gen.CodeExceptionGen;
import org.apache.bcel.generic.gen.LocalVariableGen;

public interface InstructionTargeter {
    boolean containsTarget(InstructionHandle ih);

    void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) throws ClassGenException;
}
