package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.StackInstruction;

public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2() {
        super(InstructionOpCodes.DUP2);
    }
}
