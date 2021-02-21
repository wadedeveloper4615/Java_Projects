package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ArithmeticInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FSUB extends ArithmeticInstruction {
    public FSUB(ConstantPool cp) {
        super(InstructionOpCodes.FSUB, cp);
    }
}
