package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IF_ICMPGT extends IfInstruction {
    public IF_ICMPGT() {
    }

    public IF_ICMPGT(final InstructionHandle target) {
        super(org.apache.bcel.Const.IF_ICMPGT, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPLE(super.getTarget());
    }
}
