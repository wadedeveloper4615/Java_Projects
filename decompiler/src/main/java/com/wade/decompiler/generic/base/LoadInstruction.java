package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.gen.Visitor;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction {
    public LoadInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super(canon_tag, c_tag);
    }

    public LoadInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n) {
        super(opcode, c_tag, n);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitLoadInstruction(this);
    }
}
