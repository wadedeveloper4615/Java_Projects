package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.NOP;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class NopGen extends InstructionGen {
    private InstructionOpCodes opcode;

    public NopGen(int offset, NOP instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
    }
}
