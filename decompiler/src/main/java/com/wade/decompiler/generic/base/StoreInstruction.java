package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
    public StoreInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super(canon_tag, c_tag);
    }

    public StoreInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n) {
        super(opcode, c_tag, n);
    }
}
