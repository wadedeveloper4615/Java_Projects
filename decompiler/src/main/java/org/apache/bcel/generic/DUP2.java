package org.apache.bcel.generic;

import org.apache.bcel.generic.base.PushInstruction;
import org.apache.bcel.generic.base.StackInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2() {
        super(org.apache.bcel.Const.DUP2);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP2(this);
    }
}
