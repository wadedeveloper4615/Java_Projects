package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DUP;
import com.wade.decompiler.classfile.instructions.DUP2;
import com.wade.decompiler.classfile.instructions.DUP2_X1;
import com.wade.decompiler.classfile.instructions.DUP2_X2;
import com.wade.decompiler.classfile.instructions.DUP_X1;
import com.wade.decompiler.classfile.instructions.DUP_X2;
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
public class DuplicateGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public DuplicateGen(DUP instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(DUP_X1 instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(DUP_X2 instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(DUP2 instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(DUP2_X1 instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(DUP2_X2 instr) {
        opcode = instr.getOpcode();
        type = instr.getType();
    }
}
