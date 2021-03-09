package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ATHROW;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class AThrowGen extends InstructionGen {
    private Class<?>[] exceptions;
    private InstructionOpCodes opcode;

    public AThrowGen(ATHROW instr) {
        opcode = instr.getOpcode();
        exceptions = new Class<?>[] { ExceptionConst.THROWABLE };
    }
}
