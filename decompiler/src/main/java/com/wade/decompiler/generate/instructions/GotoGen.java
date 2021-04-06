package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.GOTO;
import com.wade.decompiler.classfile.instructions.GOTO_W;
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
public class GotoGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private int offsets;

    public GotoGen(int offset, GOTO instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        offsets = instr.getIndex();
    }

    public GotoGen(int offset, GOTO_W instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        offsets = instr.getIndex();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
