package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction {
    public LoadInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, ConstantPool cp) {
        super(opcode, c_tag, cp);
    }

    public LoadInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, int n, LocalVariableTableGen localVariableTable, ConstantPool constantPool) {
        super(opcode, c_tag, n, localVariableTable, constantPool);
    }
}