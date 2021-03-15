package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IAND;
import com.wade.decompiler.classfile.instructions.LAND;
import com.wade.decompiler.classfile.instructions.type.Type;
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
public class AndGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public AndGen(int offset, IAND instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public AndGen(int offset, LAND instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
