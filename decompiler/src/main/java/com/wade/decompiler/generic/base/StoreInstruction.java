package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
    public StoreInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag, ConstantPool cp) {
        super(canon_tag, c_tag, cp);
    }

    public StoreInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n, ConstantPool constantPool) {
        super(opcode, c_tag, n, constantPool);
    }
}
