package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFLE extends IfInstruction {
    public IFLE() {
    }

    public IFLE(final InstructionHandle target) {
        super(InstructionOpCodes.IFLE, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFGT(super.getTarget());
    }
}
