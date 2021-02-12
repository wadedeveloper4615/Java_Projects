package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFNONNULL extends IfInstruction {
    public IFNONNULL() {
    }

    public IFNONNULL(final InstructionHandle target) {
        super(InstructionOpCodes.IFNONNULL, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNONNULL(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFNULL(super.getTarget());
    }
}
