package org.apache.bcel.generic;

import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.StackConsumer;
import org.apache.bcel.generic.base.TypedInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class LCMP extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public LCMP() {
        super(org.apache.bcel.Const.LCMP, (short) 1);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.LONG;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitLCMP(this);
    }
}
