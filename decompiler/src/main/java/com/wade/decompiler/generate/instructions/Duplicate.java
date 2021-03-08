package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DUP;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class Duplicate extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public Duplicate(DUP instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }
}
