package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.GOTO;
import com.wade.decompiler.classfile.instructions.GOTO_W;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class GotoGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private int index;

    public GotoGen(GOTO instr) {
        opcode = instr.getOpcode();
        index = -1;
    }

    public GotoGen(GOTO_W instr) {
        opcode = instr.getOpcode();
        index = instr.getIndex();
    }
}
