
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.PushInstruction;
import org.apache.bcel.generic.base.StackInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DUP extends StackInstruction implements PushInstruction {

    public DUP() {
        super(org.apache.bcel.Const.DUP);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP(this);
    }
}
