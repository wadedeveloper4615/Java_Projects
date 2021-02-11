
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.PopInstruction;
import org.apache.bcel.generic.base.StackInstruction;
import org.apache.bcel.generic.base.Visitor;

public class POP extends StackInstruction implements PopInstruction {

    public POP() {
        super(org.apache.bcel.Const.POP);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitStackInstruction(this);
        v.visitPOP(this);
    }
}
