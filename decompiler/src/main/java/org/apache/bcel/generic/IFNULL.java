package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFNULL extends IfInstruction {
    public IFNULL() {
    }

    public IFNULL(final InstructionHandle target) {
        super(InstructionOpCodes.IFNULL, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNULL(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFNONNULL(super.getTarget());
    }
}
