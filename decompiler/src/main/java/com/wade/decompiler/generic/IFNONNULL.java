package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFNONNULL extends IfInstruction {
    public IFNONNULL() {
    }

    public IFNONNULL(InstructionHandle target) {
        super(InstructionOpCodes.IFNONNULL, target);
    }

    @Override
    public void accept(Visitor v) {
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
