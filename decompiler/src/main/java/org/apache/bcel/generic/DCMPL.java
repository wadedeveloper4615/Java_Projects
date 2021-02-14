package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.StackConsumer;
import org.apache.bcel.generic.base.TypedInstruction;

public class DCMPL extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public DCMPL() {
        super(InstructionOpCodes.DCMPL, (short) 1);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitTypedInstruction(this);
//        v.visitStackProducer(this);
//        v.visitStackConsumer(this);
//        v.visitDCMPL(this);
//    }
//
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        return Type.DOUBLE;
//    }
}
