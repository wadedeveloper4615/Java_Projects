package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFNULL extends IfInstruction {
    public IFNULL() {
    }

    public IFNULL(InstructionHandle target) {
        super(InstructionOpCodes.IFNULL, target);
    }

    @Override
    public void accept(Visitor v) {
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
