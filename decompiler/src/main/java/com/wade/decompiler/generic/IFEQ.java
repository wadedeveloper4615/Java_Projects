package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFEQ extends IfInstruction {
    public IFEQ() {
    }

    public IFEQ(InstructionHandle target) {
        super(InstructionOpCodes.IFEQ, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget());
    }
}
