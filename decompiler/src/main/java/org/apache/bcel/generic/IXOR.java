
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.Visitor;

public class IXOR extends ArithmeticInstruction {

    public IXOR() {
        super(org.apache.bcel.Const.IXOR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIXOR(this);
    }
}
