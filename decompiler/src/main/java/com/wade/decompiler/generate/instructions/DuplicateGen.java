package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
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
public class DuplicateGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public DuplicateGen(int offset, DUP instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(int offset, DUP_X1 instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(int offset, DUP_X2 instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(int offset, DUP2 instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(int offset, DUP2_X1 instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    public DuplicateGen(int offset, DUP2_X2 instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = instr.getType();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        Expression expression = stack.pop();
        stack.push(expression);
        stack.push(expression);
        return null;//"pushed duplicate onto stack";
    }
}
