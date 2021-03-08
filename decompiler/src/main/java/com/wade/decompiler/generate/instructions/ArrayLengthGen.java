package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ARRAYLENGTH;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ArrayLengthGen extends InstructionGen {
    private InstructionOpCodes opcode;

    public ArrayLengthGen(ARRAYLENGTH instr) {
        opcode = instr.getOpcode();
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
