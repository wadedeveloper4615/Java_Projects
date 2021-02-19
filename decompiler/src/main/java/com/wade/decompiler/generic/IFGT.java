package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;

public class IFGT extends IfInstruction {
    public IFGT() {
    }

    public IFGT(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.IFGT, target, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFLE(super.getTarget(), constantPool);
    }
}
