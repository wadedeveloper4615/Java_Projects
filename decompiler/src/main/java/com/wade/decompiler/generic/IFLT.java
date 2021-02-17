package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFLT extends IfInstruction {
    public IFLT() {
    }

    public IFLT(InstructionHandle target) {
        super(InstructionOpCodes.IFLT, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget());
    }
}
