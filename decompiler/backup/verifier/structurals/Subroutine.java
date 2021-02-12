
package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.control.InstructionHandle;

public interface Subroutine {

    InstructionHandle[] getEnteringJsrInstructions();

    InstructionHandle getLeavingRET();

    InstructionHandle[] getInstructions();

    boolean contains(InstructionHandle inst);

    int[] getAccessedLocalsIndices();

    int[] getRecursivelyAccessedLocalsIndices();

    Subroutine[] subSubs();
}
