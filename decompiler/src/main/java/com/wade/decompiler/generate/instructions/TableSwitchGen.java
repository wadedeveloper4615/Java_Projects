package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.TABLESWITCH;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class TableSwitchGen extends InstructionGen {
    private InstructionOpCodes opcode;

    public TableSwitchGen(TABLESWITCH instr) {
        opcode = instr.getOpcode();
    }
}
