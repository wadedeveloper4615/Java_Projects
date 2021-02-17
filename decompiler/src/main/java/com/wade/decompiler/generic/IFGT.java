package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFGT extends IfInstruction {
    public IFGT() {
    }

    public IFGT(InstructionHandle target) {
        super(InstructionOpCodes.IFGT, target);
    }

    @Override
    public void accept(Visitor v) {
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
