package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.StackInstruction;

public class DUP2_X1 extends StackInstruction {
    public DUP2_X1(ConstantPool cp) {
        super(InstructionOpCodes.DUP2_X1, cp);
    }
}
