package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;

public class IFLE extends IfInstruction {
    public IFLE() {
    }

    public IFLE(final InstructionHandle target) {
        super(Const.IFLE, target);
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
