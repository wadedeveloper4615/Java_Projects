package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IF_ICMPGE extends IfInstruction {
    public IF_ICMPGE() {
    }

    public IF_ICMPGE(final InstructionHandle target) {
        super(org.apache.bcel.Const.IF_ICMPGE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLT(super.getTarget());
    }
}
