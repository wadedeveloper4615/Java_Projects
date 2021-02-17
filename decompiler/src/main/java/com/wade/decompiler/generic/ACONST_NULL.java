package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL() {
        super(Const.ACONST_NULL, 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitACONST_NULL(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.NULL;
    }
}
