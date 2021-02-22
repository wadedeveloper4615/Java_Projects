package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFLT extends IfInstruction {
    public IFLT(ConstantPool cp) {
        super(InstructionOpCodes.IFLT, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(constantPool);
    }
}
