package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

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
