
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFLT extends IfInstruction {

    public IFLT() {
    }

    public IFLT(final InstructionHandle target) {
        super(org.apache.bcel.Const.IFLT, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLT(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget());
    }
}
