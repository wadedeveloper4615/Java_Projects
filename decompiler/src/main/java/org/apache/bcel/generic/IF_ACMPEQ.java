
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IF_ACMPEQ extends IfInstruction {

    public IF_ACMPEQ() {
    }

    public IF_ACMPEQ(final InstructionHandle target) {
        super(org.apache.bcel.Const.IF_ACMPEQ, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPNE(super.getTarget());
    }
}
