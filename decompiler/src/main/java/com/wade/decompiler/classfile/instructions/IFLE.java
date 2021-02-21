package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.enums.InstructionOpCodes;

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
