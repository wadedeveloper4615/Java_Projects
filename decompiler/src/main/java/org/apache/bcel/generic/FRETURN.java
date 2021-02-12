package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.base.Visitor;

public class FRETURN extends ReturnInstruction {
    public FRETURN() {
        super(InstructionOpCodes.FRETURN);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitFRETURN(this);
    }
}
