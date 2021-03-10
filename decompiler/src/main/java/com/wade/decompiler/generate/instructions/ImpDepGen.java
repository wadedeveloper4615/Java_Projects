package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IMPDEP1;
import com.wade.decompiler.classfile.instructions.IMPDEP2;

public class ImpDepGen extends InstructionGen {
    public ImpDepGen(int offset, IMPDEP1 instr) {
        super(offset, instr.getLength());
    }

    public ImpDepGen(int offset, IMPDEP2 instr) {
        super(offset, instr.getLength());
    }
}
