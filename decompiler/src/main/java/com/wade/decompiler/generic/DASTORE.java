package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;

public class DASTORE extends ArrayInstruction implements StackConsumer {
    public DASTORE() {
        super(InstructionOpCodes.DASTORE);
    }
}
