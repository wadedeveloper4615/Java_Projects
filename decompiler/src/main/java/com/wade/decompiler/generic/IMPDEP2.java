package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.gen.Visitor;

public class IMPDEP2 extends Instruction {
    public IMPDEP2() {
        super(InstructionOpCodes.IMPDEP2, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitIMPDEP2(this);
    }
}
