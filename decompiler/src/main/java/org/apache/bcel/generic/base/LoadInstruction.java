package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction {
    protected LoadInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super(canon_tag, c_tag);
    }

    protected LoadInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, final int n) {
        super(opcode, c_tag, n);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitLoadInstruction(this);
    }
}
