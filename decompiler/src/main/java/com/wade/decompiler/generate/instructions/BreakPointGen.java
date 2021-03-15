package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.BREAKPOINT;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BreakPointGen extends InstructionGen {
    private InstructionOpCodes opcode;

    public BreakPointGen(int offset, BREAKPOINT instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
