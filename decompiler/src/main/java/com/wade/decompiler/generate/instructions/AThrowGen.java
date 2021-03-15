package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ATHROW;
import com.wade.decompiler.constants.ExceptionConst;
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
public class AThrowGen extends InstructionGen {
    private Class<?>[] exceptions;
    private InstructionOpCodes opcode;

    public AThrowGen(int offset, ATHROW instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        exceptions = new Class<?>[] { ExceptionConst.THROWABLE };
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
