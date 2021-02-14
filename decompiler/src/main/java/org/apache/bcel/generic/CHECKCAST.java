package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.LoadClass;
import org.apache.bcel.generic.base.StackConsumer;

public class CHECKCAST extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer, StackConsumer {
    public CHECKCAST() {
    }

    public CHECKCAST(final int index) {
        super(InstructionOpCodes.CHECKCAST, index);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitLoadClass(this);
//        v.visitExceptionThrower(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitTypedInstruction(this);
//        v.visitCPInstruction(this);
//        v.visitCHECKCAST(this);
//    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.CLASS_CAST_EXCEPTION);
    }
//
//    @Override
//    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
//        Type t = getType(cpg);
//        if (t instanceof ArrayType) {
//            t = ((ArrayType) t).getBasicType();
//        }
//        return (t instanceof ObjectType) ? (ObjectType) t : null;
//    }
}
