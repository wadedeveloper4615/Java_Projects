package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ARRAYLENGTH;
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
public class ArrayLengthGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Class<?>[] exceptions;

    public ArrayLengthGen(int offset, ARRAYLENGTH instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        exceptions = new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
