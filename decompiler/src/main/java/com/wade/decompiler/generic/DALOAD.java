package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackProducer;

public class DALOAD extends ArrayInstruction implements StackProducer {
    public DALOAD() {
        super(InstructionOpCodes.DALOAD);
    }
}
