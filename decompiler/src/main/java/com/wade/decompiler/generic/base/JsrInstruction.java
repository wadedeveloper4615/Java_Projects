package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.ConstantPoolGen;

public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch, TypedInstruction, StackProducer {
    public JsrInstruction() {
    }

    public JsrInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return new ReturnaddressType(physicalSuccessor());
    }

    public InstructionHandle physicalSuccessor() {
        InstructionHandle ih = super.getTarget();
        // Rewind!
        while (ih.getPrev() != null) {
            ih = ih.getPrev();
        }
        // Find the handle for "this" JsrInstruction object.
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
        // Return the physical successor
        return toThis.getNext();
    }
}
