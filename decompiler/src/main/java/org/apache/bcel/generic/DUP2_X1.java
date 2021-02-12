package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.StackInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DUP2_X1 extends StackInstruction {
    public DUP2_X1() {
        super(InstructionOpCodes.DUP2_X1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP2_X1(this);
    }
}
