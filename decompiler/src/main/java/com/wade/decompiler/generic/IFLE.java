package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFLE extends IfInstruction {
    public IFLE() {
    }

    public IFLE(InstructionHandle target) {
        super(InstructionOpCodes.IFLE, target);
    }

    @Override
    public void accept(Visitor v) {
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
