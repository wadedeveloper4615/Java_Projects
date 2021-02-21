package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StackInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DUP2_X1 extends StackInstruction {
    public DUP2_X1(ConstantPool cp) {
        super(InstructionOpCodes.DUP2_X1, cp);
    }
}
