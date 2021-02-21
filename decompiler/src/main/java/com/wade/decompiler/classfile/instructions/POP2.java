package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.PopInstruction;
import com.wade.decompiler.classfile.instructions.base.StackInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class POP2 extends StackInstruction implements PopInstruction {
    public POP2(ConstantPool cp) {
        super(InstructionOpCodes.POP2, cp);
    }
}
