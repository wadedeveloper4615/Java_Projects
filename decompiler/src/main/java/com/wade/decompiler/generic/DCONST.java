package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class DCONST extends Instruction implements ConstantPushInstruction {
    private double value;

    DCONST() {
    }

    public DCONST(final double f) {
        super(Const.DCONST_0, 1);
        if (f == 0.0) {
            super.setOpcode(Const.DCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(Const.DCONST_1);
        } else {
            throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
        }
        value = f;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitDCONST(this);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.DOUBLE;
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }
}
