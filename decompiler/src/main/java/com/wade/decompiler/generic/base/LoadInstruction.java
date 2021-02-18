package com.wade.decompiler.generic.base;

import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction {
    public LoadInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super(canon_tag, c_tag);
    }

    public LoadInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n) {
        super(opcode, c_tag, n);
    }
}
