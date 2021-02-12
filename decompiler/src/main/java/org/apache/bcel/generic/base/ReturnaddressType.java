
package org.apache.bcel.generic.base;

import org.apache.bcel.Const;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.control.InstructionHandle;

public class ReturnaddressType extends Type {

    public static final ReturnaddressType NO_TARGET = new ReturnaddressType();
    private InstructionHandle returnTarget;

    private ReturnaddressType() {
        super(Const.T_ADDRESS, "<return address>");
    }

    public ReturnaddressType(final InstructionHandle returnTarget) {
        super(Const.T_ADDRESS, "<return address targeting " + returnTarget + ">");
        this.returnTarget = returnTarget;
    }

    @Override
    public int hashCode() {
        if (returnTarget == null) {
            return 0;
        }
        return returnTarget.hashCode();
    }

    @Override
    public boolean equals(final Object rat) {
        if (!(rat instanceof ReturnaddressType)) {
            return false;
        }
        final ReturnaddressType that = (ReturnaddressType) rat;
        if (this.returnTarget == null || that.returnTarget == null) {
            return that.returnTarget == this.returnTarget;
        }
        return that.returnTarget.equals(this.returnTarget);
    }

    public InstructionHandle getTarget() {
        return returnTarget;
    }
}