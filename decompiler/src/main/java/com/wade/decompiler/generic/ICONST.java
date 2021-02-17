package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class ICONST extends Instruction implements ConstantPushInstruction {
    private int value;

    public ICONST() {
    }

    public ICONST(final int i) {
        super(Const.ICONST_0, 1);
        if ((i >= -1) && (i <= 5)) {
            super.setOpcode((short) (Const.ICONST_0 + i)); // Even works for i == -1
        } else {
            throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
        }
        value = i;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitICONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.INT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }
}
