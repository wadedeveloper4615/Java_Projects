package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFGT extends IfInstruction {
    public IFGT() {
    }

    public IFGT(final InstructionHandle target) {
        super(InstructionOpCodes.IFGT, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFGT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFLE(super.getTarget());
    }
}
