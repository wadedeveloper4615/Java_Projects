package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.StackProducer;

public class CALOAD extends ArrayInstruction implements StackProducer {
    public CALOAD() {
        super(InstructionOpCodes.CALOAD);
    }
}
