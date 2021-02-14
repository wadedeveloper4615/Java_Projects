package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.InvokeInstruction;

public class INVOKESPECIAL extends InvokeInstruction {
    public INVOKESPECIAL() {
    }

    public INVOKESPECIAL(final int index) {
        super(InstructionOpCodes.INVOKESPECIAL, index);
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
//        v.visitINVOKESPECIAL(this);
//    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        // out.writeShort(super.getIndex());
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.UNSATISFIED_LINK_ERROR);
    }
}
