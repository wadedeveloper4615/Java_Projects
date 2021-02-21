package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.PushInstruction;
import com.wade.decompiler.classfile.instructions.base.StackInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DUP2 extends StackInstruction implements PushInstruction {
    public DUP2(ConstantPool cp) {
        super(InstructionOpCodes.DUP2, cp);
    }
}
