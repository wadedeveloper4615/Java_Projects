package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackProducer;

public class BALOAD extends ArrayInstruction implements StackProducer {
    public BALOAD() {
        super(InstructionOpCodes.BALOAD);
    }
}
