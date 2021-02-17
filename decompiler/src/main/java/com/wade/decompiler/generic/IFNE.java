package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFNE extends IfInstruction {
    public IFNE() {
    }

    public IFNE(InstructionHandle target) {
        super(InstructionOpCodes.IFNE, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNE(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFEQ(super.getTarget());
    }
}
