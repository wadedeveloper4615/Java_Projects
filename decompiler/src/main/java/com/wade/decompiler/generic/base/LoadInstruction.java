package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction {
    public LoadInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag, ConstantPool cp) {
        super(canon_tag, c_tag, cp);
    }

    public LoadInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n, ConstantPool constantPool) {
        super(opcode, c_tag, n, constantPool);
    }
}
