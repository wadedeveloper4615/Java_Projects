package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.generic.type.Type;

public class DCMPL extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public DCMPL() {
        super(InstructionOpCodes.DCMPL, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitDCMPL(this);
    }

    @Override
    public Type getType(ConstantPoolGen cp) {
        return Type.DOUBLE;
    }
}
