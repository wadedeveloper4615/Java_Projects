package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

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
