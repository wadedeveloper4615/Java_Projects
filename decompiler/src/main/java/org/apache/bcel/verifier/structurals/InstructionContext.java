
package org.apache.bcel.verifier.structurals;

import java.util.ArrayList;

import org.apache.bcel.generic.control.InstructionHandle;

public interface InstructionContext {

    int getTag();

    void setTag(int tag);

    boolean execute(Frame inFrame, ArrayList<InstructionContext> executionPredecessors, InstConstraintVisitor icv, ExecutionVisitor ev);

    Frame getInFrame();

    Frame getOutFrame(ArrayList<InstructionContext> executionPredecessors);

    InstructionHandle getInstruction();

    InstructionContext[] getSuccessors();

    ExceptionHandler[] getExceptionHandlers();
}
