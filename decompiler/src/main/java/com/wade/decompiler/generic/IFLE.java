package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFLE extends IfInstruction {
    public IFLE() {
    }

    public IFLE(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFLE, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFGT(super.getTarget(), constantPool);
    }
}
