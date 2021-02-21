package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
