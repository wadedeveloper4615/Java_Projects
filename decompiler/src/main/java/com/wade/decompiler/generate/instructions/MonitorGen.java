package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.MONITORENTER;
import com.wade.decompiler.classfile.instructions.MONITOREXIT;
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
public class MonitorGen extends InstructionGen {
    private InstructionOpCodes opcode;

    public MonitorGen(int offset, MONITORENTER instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
    }

    public MonitorGen(int offset, MONITOREXIT instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
