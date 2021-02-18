package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackConsumer;

public class IASTORE extends ArrayInstruction implements StackConsumer {
    public IASTORE() {
        super(InstructionOpCodes.IASTORE);
    }
}
