package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class LCONST extends Instruction implements ConstantPushInstruction {
    private long value;

    LCONST() {
    }

    public LCONST(final long l) {
        super(Const.LCONST_0, (short) 1);
        if (l == 0) {
            super.setOpcode(Const.LCONST_0);
        } else if (l == 1) {
            super.setOpcode(Const.LCONST_1);
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitLCONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.LONG;
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }
}
