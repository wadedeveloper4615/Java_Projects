
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.StackInstruction;
import org.apache.bcel.generic.base.Visitor;

public class DUP_X1 extends StackInstruction {

    public DUP_X1() {
        super(org.apache.bcel.Const.DUP_X1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X1(this);
    }
}
