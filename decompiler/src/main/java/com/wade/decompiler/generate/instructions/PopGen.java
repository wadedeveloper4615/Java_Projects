package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.POP;
import com.wade.decompiler.classfile.instructions.POP2;
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
public class PopGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public PopGen(POP instr) {
        opcode = instr.getOpcode();
        type = Type.UNKNOWN;
    }

    public PopGen(POP2 instr) {
        opcode = instr.getOpcode();
        type = Type.UNKNOWN;
    }
}
