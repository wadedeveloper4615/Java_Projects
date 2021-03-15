package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DADD;
import com.wade.decompiler.classfile.instructions.FADD;
import com.wade.decompiler.classfile.instructions.IADD;
import com.wade.decompiler.classfile.instructions.LADD;
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
public class AddGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public AddGen(int offset, DADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public AddGen(int offset, FADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public AddGen(int offset, IADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public AddGen(int offset, LADD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
