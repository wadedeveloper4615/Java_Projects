package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArithmeticInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DADD extends ArithmeticInstruction {
    public DADD(ConstantPool cp) {
        super(InstructionOpCodes.DADD, cp);
    }
}
