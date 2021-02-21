package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

public class IFLT extends IfInstruction {
    public IFLT() {
    }

    public IFLT(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFLT, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(super.getTarget(), constantPool);
    }
}
