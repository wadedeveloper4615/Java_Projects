package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.InvokeInstruction;

public class INVOKESTATIC extends InvokeInstruction {
    public INVOKESTATIC() {
    }

    public INVOKESTATIC(final int index) {
        super(InstructionOpCodes.INVOKESTATIC, index);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitExceptionThrower(this);
//        v.visitTypedInstruction(this);
//        v.visitStackConsumer(this);
//        v.visitStackProducer(this);
//        v.visitLoadClass(this);
//        v.visitCPInstruction(this);
//        v.visitFieldOrMethod(this);
//        v.visitInvokeInstruction(this);
//        v.visitINVOKESTATIC(this);
//    }
//
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
////        out.writeByte(super.getOpcode().getOpcode());
////        out.writeShort(super.getIndex());
//    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
