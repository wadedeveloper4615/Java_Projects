
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.IfInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class IFEQ extends IfInstruction {

    public IFEQ() {
    }

    public IFEQ(final InstructionHandle target) {
        super(org.apache.bcel.Const.IFEQ, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFEQ(this);
    }

    @Override
    public IfInstruction negate() {
        return new IFNE(super.getTarget());
    }
}
