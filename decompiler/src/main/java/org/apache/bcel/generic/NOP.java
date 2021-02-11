
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;

public class NOP extends Instruction {

    public NOP() {
        super(org.apache.bcel.Const.NOP, (short) 1);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitNOP(this);
    }
}
