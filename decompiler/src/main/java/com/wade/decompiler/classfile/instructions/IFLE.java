package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFLE extends IfInstruction {
    public IFLE(ConstantPool cp) {
        super(InstructionOpCodes.IFLE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFGT(constantPool);
    }
}
