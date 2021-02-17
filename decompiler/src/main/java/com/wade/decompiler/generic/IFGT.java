package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFGT extends IfInstruction {
    public IFGT() {
    }

    public IFGT(final InstructionHandle target) {
        super(Const.IFGT, target);
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
