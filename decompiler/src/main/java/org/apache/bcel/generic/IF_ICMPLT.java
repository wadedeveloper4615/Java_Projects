
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IF_ICMPLT extends IfInstruction {

    public IF_ICMPLT() {
    }

    public IF_ICMPLT(final InstructionHandle target) {
        super(org.apache.bcel.Const.IF_ICMPLT, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPGE(super.getTarget());
    }
}
