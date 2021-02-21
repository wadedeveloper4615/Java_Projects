package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFGE extends IfInstruction {
    public IFGE() {
    }

    public IFGE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFGE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFLT(super.getTarget(), constantPool);
    }
}
