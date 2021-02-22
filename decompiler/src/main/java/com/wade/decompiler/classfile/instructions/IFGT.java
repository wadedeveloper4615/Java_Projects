package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFGT extends IfInstruction {
    public IFGT(ConstantPool cp) {
        super(InstructionOpCodes.IFGT, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFLE(constantPool);
    }
}
