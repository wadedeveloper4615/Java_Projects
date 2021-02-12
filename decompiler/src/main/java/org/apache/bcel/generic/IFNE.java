package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFNE extends IfInstruction {
    public IFNE() {
    }

    public IFNE(final InstructionHandle target) {
        super(org.apache.bcel.Const.IFNE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }
}
