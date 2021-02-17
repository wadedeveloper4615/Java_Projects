package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class FCONST extends Instruction implements ConstantPushInstruction {
    private float value;

    public FCONST() {
    }

    public FCONST(final float f) {
        super(Const.FCONST_0, 1);
        if (f == 0.0) {
            super.setOpcode(Const.FCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(Const.FCONST_1);
        } else if (f == 2.0) {
            super.setOpcode(Const.FCONST_2);
        } else {
            throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
        }
        value = f;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitFCONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }

    @Override
    public Number getValue() {
        return Float.valueOf(value);
    }
}
