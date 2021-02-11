
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.ArithmeticInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ISHR extends ArithmeticInstruction {

    public ISHR() {
        super(org.apache.bcel.Const.ISHR);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitISHR(this);
    }
}
