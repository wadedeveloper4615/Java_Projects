package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFGE extends IfInstruction {
    public IFGE() {
    }

    public IFGE(final InstructionHandle target) {
        super(InstructionOpCodes.IFGE, target);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackConsumer(this);
//        v.visitBranchInstruction(this);
//        v.visitIfInstruction(this);
//        v.visitIFGE(this);
//    }

    @Override
    public IfInstruction negate() {
        return new IFLT(super.getTarget());
    }
}
