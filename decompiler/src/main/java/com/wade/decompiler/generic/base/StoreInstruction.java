package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.gen.Visitor;

public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
    public StoreInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super(canon_tag, c_tag);
    }

    public StoreInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n) {
        super(opcode, c_tag, n);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitStoreInstruction(this);
    }
}
