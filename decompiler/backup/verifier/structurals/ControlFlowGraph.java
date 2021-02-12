
package org.apache.bcel.verifier.structurals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.bcel.generic.ATHROW;
import org.apache.bcel.generic.RET;
import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.GotoInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.JsrInstruction;
import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.gen.MethodGen;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;

public class ControlFlowGraph {

    private class InstructionContextImpl implements InstructionContext {

        private int TAG;

        private final InstructionHandle instruction;

        private final Map<InstructionContext, Frame> inFrames; // key: the last-executed JSR

        private final Map<InstructionContext, Frame> outFrames; // key: the last-executed JSR

        private List<InstructionContext> executionPredecessors = null; // Type: InstructionContext

        public InstructionContextImpl(final InstructionHandle inst) {
            if (inst == null) {
                throw new AssertionViolatedException("Cannot instantiate InstructionContextImpl from NULL.");
            }

            instruction = inst;
            inFrames = new HashMap<>();
            outFrames = new HashMap<>();
        }

        // TODO: implement caching!
        private InstructionHandle[] _getSuccessors() {
            final InstructionHandle[] empty = new InstructionHandle[0];
            final InstructionHandle[] single = new InstructionHandle[1];

            final Instruction inst = getInstruction().getInstruction();

            if (inst instanceof RET) {
                final Subroutine s = subroutines.subroutineOf(getInstruction());
                if (s == null) { // return empty;
                    // RET in dead code. "empty" would be the correct answer, but we know something
                    // about the surrounding project...
                    throw new AssertionViolatedException("Asking for successors of a RET in dead code?!");
                }

//TODO: remove. Only JustIce must not use it, but foreign users of the ControlFlowGraph
//      will want it. Thanks Johannes Wust.
//throw new AssertionViolatedException("DID YOU REALLY WANT TO ASK FOR RET'S SUCCS?");

                final InstructionHandle[] jsrs = s.getEnteringJsrInstructions();
                final InstructionHandle[] ret = new InstructionHandle[jsrs.length];
                for (int i = 0; i < jsrs.length; i++) {
                    ret[i] = jsrs[i].getNext();
                }
                return ret;
            }

            // Terminates method normally.
            if (inst instanceof ReturnInstruction) {
                return empty;
            }

            // Terminates method abnormally, because JustIce mandates
            // subroutines not to be protected by exception handlers.
            if (inst instanceof ATHROW) {
                return empty;
            }

            // See method comment.
            if (inst instanceof JsrInstruction) {
                single[0] = ((JsrInstruction) inst).getTarget();
                return single;
            }

            if (inst instanceof GotoInstruction) {
                single[0] = ((GotoInstruction) inst).getTarget();
                return single;
            }

            if (inst instanceof BranchInstruction) {
                if (inst instanceof Select) {
                    // BCEL's getTargets() returns only the non-default targets,
                    // thanks to Eli Tilevich for reporting.
                    final InstructionHandle[] matchTargets = ((Select) inst).getTargets();
                    final InstructionHandle[] ret = new InstructionHandle[matchTargets.length + 1];
                    ret[0] = ((Select) inst).getTarget();
                    System.arraycopy(matchTargets, 0, ret, 1, matchTargets.length);
                    return ret;
                }
                final InstructionHandle[] pair = new InstructionHandle[2];
                pair[0] = getInstruction().getNext();
                pair[1] = ((BranchInstruction) inst).getTarget();
                return pair;
            }

            // default case: Fall through.
            single[0] = getInstruction().getNext();
            return single;
        }

        @Override
        public boolean execute(final Frame inFrame, final ArrayList<InstructionContext> execPreds, final InstConstraintVisitor icv, final ExecutionVisitor ev) {

            @SuppressWarnings("unchecked") // OK because execPreds is compatible type
            final List<InstructionContext> clone = (List<InstructionContext>) execPreds.clone();
            executionPredecessors = clone;

            // sanity check
            if ((lastExecutionJSR() == null) && (subroutines.subroutineOf(getInstruction()) != subroutines.getTopLevel())) {
                throw new AssertionViolatedException("Huh?! Am I '" + this + "' part of a subroutine or not?");
            }
            if ((lastExecutionJSR() != null) && (subroutines.subroutineOf(getInstruction()) == subroutines.getTopLevel())) {
                throw new AssertionViolatedException("Huh?! Am I '" + this + "' part of a subroutine or not?");
            }

            Frame inF = inFrames.get(lastExecutionJSR());
            if (inF == null) {// no incoming frame was set, so set it.
                inFrames.put(lastExecutionJSR(), inFrame);
                inF = inFrame;
            } else {// if there was an "old" inFrame
                if (inF.equals(inFrame)) { // shortcut: no need to merge equal frames.
                    return false;
                }
                if (!mergeInFrames(inFrame)) {
                    return false;
                }
            }

            // Now we're sure the inFrame has changed!

            // new inFrame is already merged in, see above.
            final Frame workingFrame = inF.getClone();

            try {
                // This verifies the InstructionConstraint for the current
                // instruction, but does not modify the workingFrame object.
//InstConstraintVisitor icv = InstConstraintVisitor.getInstance(VerifierFactory.getVerifier(method_gen.getClassName()));
                icv.setFrame(workingFrame);
                getInstruction().accept(icv);
            } catch (final StructuralCodeConstraintException ce) {
                ce.extendMessage("", "\nInstructionHandle: " + getInstruction() + "\n");
                ce.extendMessage("", "\nExecution Frame:\n" + workingFrame);
                extendMessageWithFlow(ce);
                throw ce;
            }

            // This executes the Instruction.
            // Therefore the workingFrame object is modified.
//ExecutionVisitor ev = ExecutionVisitor.getInstance(VerifierFactory.getVerifier(method_gen.getClassName()));
            ev.setFrame(workingFrame);
            getInstruction().accept(ev);
            // getInstruction().accept(ExecutionVisitor.withFrame(workingFrame));
            outFrames.put(lastExecutionJSR(), workingFrame);

            return true; // new inFrame was different from old inFrame so merging them
                         // yielded a different this.inFrame.
        }

        private void extendMessageWithFlow(final StructuralCodeConstraintException e) {
            final String s = "Execution flow:\n";
            e.extendMessage("", s + getExecutionChain());
        }

        @Override
        public ExceptionHandler[] getExceptionHandlers() {
            return exceptionhandlers.getExceptionHandlers(getInstruction());
        }

        private String getExecutionChain() {
            String s = this.toString();
            for (int i = executionPredecessors.size() - 1; i >= 0; i--) {
                s = executionPredecessors.get(i) + "\n" + s;
            }
            return s;
        }

        @Override
        public Frame getInFrame() {
            Frame org;

            final InstructionContext jsr = lastExecutionJSR();

            org = inFrames.get(jsr);

            if (org == null) {
                throw new AssertionViolatedException("inFrame not set! This:\n" + this + "\nInFrames: '" + inFrames + "'.");
            }
            return org.getClone();
        }

        @Override
        public InstructionHandle getInstruction() {
            return instruction;
        }

        @Override
        public Frame getOutFrame(final ArrayList<InstructionContext> execChain) {
            executionPredecessors = execChain;

            Frame org;

            final InstructionContext jsr = lastExecutionJSR();

            org = outFrames.get(jsr);

            if (org == null) {
                throw new AssertionViolatedException("outFrame not set! This:\n" + this + "\nExecutionChain: " + getExecutionChain() + "\nOutFrames: '" + outFrames + "'.");
            }
            return org.getClone();
        }

        @Override
        public InstructionContext[] getSuccessors() {
            return contextsOf(_getSuccessors());
        }

        @Override
        public int getTag() {
            return TAG;
        }

        private InstructionContextImpl lastExecutionJSR() {

            final int size = executionPredecessors.size();
            int retcount = 0;

            for (int i = size - 1; i >= 0; i--) {
                final InstructionContextImpl current = (InstructionContextImpl) (executionPredecessors.get(i));
                final Instruction currentlast = current.getInstruction().getInstruction();
                if (currentlast instanceof RET) {
                    retcount++;
                }
                if (currentlast instanceof JsrInstruction) {
                    retcount--;
                    if (retcount == -1) {
                        return current;
                    }
                }
            }
            return null;
        }

        private boolean mergeInFrames(final Frame inFrame) {
            // TODO: Can be performance-improved.
            final Frame inF = inFrames.get(lastExecutionJSR());
            final OperandStack oldstack = inF.getStack().getClone();
            final LocalVariables oldlocals = inF.getLocals().getClone();
            try {
                inF.getStack().merge(inFrame.getStack());
                inF.getLocals().merge(inFrame.getLocals());
            } catch (final StructuralCodeConstraintException sce) {
                extendMessageWithFlow(sce);
                throw sce;
            }
            return !(oldstack.equals(inF.getStack()) && oldlocals.equals(inF.getLocals()));
        }

        @Override
        public void setTag(final int tag) { // part of InstructionContext interface
            TAG = tag;
        }

        @Override
        public String toString() {
            // TODO: Put information in the brackets, e.g.
            // Is this an ExceptionHandler? Is this a RET? Is this the start of
            // a subroutine?
            final String ret = getInstruction().toString(false) + "\t[InstructionContext]";
            return ret;
        }

    } // End Inner InstructionContextImpl Class.

    //
    // private final MethodGen method_gen;

    private final Subroutines subroutines;

    private final ExceptionHandlers exceptionhandlers;

    private final Map<InstructionHandle, InstructionContext> instructionContexts = new HashMap<>();

    public ControlFlowGraph(final MethodGen method_gen) {
        this(method_gen, true);
    }

    public ControlFlowGraph(final MethodGen method_gen, final boolean enableJustIceCheck) {
        subroutines = new Subroutines(method_gen, enableJustIceCheck);
        exceptionhandlers = new ExceptionHandlers(method_gen);

        final InstructionHandle[] instructionhandles = method_gen.getInstructionList().getInstructionHandles();
        for (final InstructionHandle instructionhandle : instructionhandles) {
            instructionContexts.put(instructionhandle, new InstructionContextImpl(instructionhandle));
        }

        // this.method_gen = method_gen;
    }

    public InstructionContext contextOf(final InstructionHandle inst) {
        final InstructionContext ic = instructionContexts.get(inst);
        if (ic == null) {
            throw new AssertionViolatedException("InstructionContext requested for an InstructionHandle that's not known!");
        }
        return ic;
    }

    public InstructionContext[] contextsOf(final InstructionHandle[] insts) {
        final InstructionContext[] ret = new InstructionContext[insts.length];
        for (int i = 0; i < insts.length; i++) {
            ret[i] = contextOf(insts[i]);
        }
        return ret;
    }

    public InstructionContext[] getInstructionContexts() {
        final InstructionContext[] ret = new InstructionContext[instructionContexts.size()];
        return instructionContexts.values().toArray(ret);
    }

    public boolean isDead(final InstructionHandle i) {
        return subroutines.subroutineOf(i) == null;
    }
}
