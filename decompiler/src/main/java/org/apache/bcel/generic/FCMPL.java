package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.StackConsumer;
import org.apache.bcel.generic.base.TypedInstruction;

public class FCMPL extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public FCMPL() {
        super(InstructionOpCodes.FCMPL, (short) 1);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitFCMPL(this);
//    }
//
//
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        return Type.FLOAT;
//    }
}
