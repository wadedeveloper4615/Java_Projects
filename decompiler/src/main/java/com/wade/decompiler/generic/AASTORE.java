package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.gen.Visitor;

public class AASTORE extends ArrayInstruction implements StackConsumer {
    public AASTORE() {
        super(Const.AASTORE);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitAASTORE(this);
    }
}
