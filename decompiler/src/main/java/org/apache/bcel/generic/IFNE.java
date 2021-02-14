package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFNE extends IfInstruction {
    public IFNE() {
    }

    public IFNE(final InstructionHandle target) {
        super(InstructionOpCodes.IFNE, target);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackConsumer(this);
//        v.visitBranchInstruction(this);
//        v.visitIfInstruction(this);
//        v.visitIFNE(this);
//    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }
}
