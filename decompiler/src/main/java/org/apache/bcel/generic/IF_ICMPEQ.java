
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IF_ICMPEQ extends IfInstruction {

    public IF_ICMPEQ() {
    }

    public IF_ICMPEQ(final InstructionHandle target) {
        super(org.apache.bcel.Const.IF_ICMPEQ, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPNE(super.getTarget());
    }
}
