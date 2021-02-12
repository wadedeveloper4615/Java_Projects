
package org.apache.bcel.verifier.structurals;

import java.util.ArrayList;

import org.apache.bcel.generic.control.InstructionHandle;

public interface InstructionContext {

    boolean execute(Frame inFrame, ArrayList<InstructionContext> executionPredecessors, InstConstraintVisitor icv, ExecutionVisitor ev);

    ExceptionHandler[] getExceptionHandlers();

    Frame getInFrame();

    InstructionHandle getInstruction();

    Frame getOutFrame(ArrayList<InstructionContext> executionPredecessors);

    InstructionContext[] getSuccessors();

    int getTag();

    void setTag(int tag);
}
