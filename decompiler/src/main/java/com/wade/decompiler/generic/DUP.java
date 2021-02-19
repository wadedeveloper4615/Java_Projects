package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.StackInstruction;

public class DUP extends StackInstruction implements PushInstruction {
    public DUP(ConstantPool cp) {
        super(InstructionOpCodes.DUP, cp);
    }
}
