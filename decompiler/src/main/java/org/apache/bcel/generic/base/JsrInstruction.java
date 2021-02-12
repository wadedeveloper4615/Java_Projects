package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.StackProducer;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch, TypedInstruction, StackProducer {
    public JsrInstruction() {
    }

    public JsrInstruction(InstructionOpCodes opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return new ReturnaddressType(physicalSuccessor());
    }

    public InstructionHandle physicalSuccessor() {
        InstructionHandle ih = super.getTarget();
        while (ih.getPrev() != null) {
            ih = ih.getPrev();
        }
        while (ih.getInstruction() != this) {
            ih = ih.getNext();
        }
        final InstructionHandle toThis = ih;
        while (ih != null) {
            ih = ih.getNext();
            if ((ih != null) && (ih.getInstruction() == this)) {
                throw new IllegalStateException("physicalSuccessor() called on a shared JsrInstruction.");
            }
        }
        return toThis.getNext();
    }
}
