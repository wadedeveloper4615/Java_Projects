package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LOOKUPSWITCH extends Select {

    public LOOKUPSWITCH(InstructionOpCodes opcode, int[] match, ConstantPool cp) {
        super(opcode, match, cp);
    }
}
