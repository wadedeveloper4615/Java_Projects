package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;

public class AASTORE extends ArrayInstruction implements StackConsumer {
    public AASTORE() {
        super(InstructionOpCodes.AASTORE);
    }
}
