package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFGE extends IfInstruction {
    public IFGE(ConstantPool cp) {
        super(InstructionOpCodes.IFGE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFLT(constantPool);
    }
}
