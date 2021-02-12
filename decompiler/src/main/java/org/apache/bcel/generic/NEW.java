package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.generic.base.AllocationInstruction;
import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.LoadClass;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;

public class NEW extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower, StackProducer {
    public NEW() {
    }

    public NEW(final int index) {
        super(org.apache.bcel.Const.NEW, index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitNEW(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INSTANTIATION_ERROR);
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        return (ObjectType) getType(cpg);
    }
}
