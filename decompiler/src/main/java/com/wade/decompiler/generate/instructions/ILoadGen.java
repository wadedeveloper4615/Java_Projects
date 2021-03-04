package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ILOAD;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ILoadGen extends InstructionGen {
    private LocalVariableGen localVariableReference;
    private InstructionOpCodes opcode;

    public ILoadGen(ILOAD instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariable();
    }
}
