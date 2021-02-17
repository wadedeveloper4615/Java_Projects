package com.wade.decompiler.generic.type;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.InstructionHandle;

public class ReturnaddressType extends Type {
    public static ReturnaddressType NO_TARGET = new ReturnaddressType();
    private InstructionHandle returnTarget;

    private ReturnaddressType() {
        super(Const.T_ADDRESS, "<return address>");
    }

    public ReturnaddressType(InstructionHandle returnTarget) {
        super(Const.T_ADDRESS, "<return address targeting " + returnTarget + ">");
        this.returnTarget = returnTarget;
    }

    @Override
    public boolean equals(Object rat) {
        if (!(rat instanceof ReturnaddressType)) {
            return false;
        }
        ReturnaddressType that = (ReturnaddressType) rat;
        if (this.returnTarget == null || that.returnTarget == null) {
            return that.returnTarget == this.returnTarget;
        }
        return that.returnTarget.equals(this.returnTarget);
    }

    public InstructionHandle getTarget() {
        return returnTarget;
    }

    @Override
    public int hashCode() {
        if (returnTarget == null) {
            return 0;
        }
        return returnTarget.hashCode();
    }
}
