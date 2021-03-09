package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.BREAKPOINT;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BreakPointGen extends InstructionGen {
    private InstructionOpCodes opcode;

    public BreakPointGen(BREAKPOINT instr) {
        opcode = instr.getOpcode();
    }
}
