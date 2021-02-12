package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IF_ICMPLE extends IfInstruction {
    public IF_ICMPLE() {
    }

    public IF_ICMPLE(final InstructionHandle target) {
        super(org.apache.bcel.Const.IF_ICMPLE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGT(super.getTarget());
    }
}
