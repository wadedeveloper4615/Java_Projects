package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFNONNULL extends IfInstruction {
    public IFNONNULL(ConstantPool cp) {
        super(InstructionOpCodes.IFNONNULL, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFNULL(constantPool);
    }
}
