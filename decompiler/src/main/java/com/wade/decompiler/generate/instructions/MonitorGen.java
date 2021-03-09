package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.MONITORENTER;
import com.wade.decompiler.classfile.instructions.MONITOREXIT;
import com.wade.decompiler.constants.ExceptionConst;
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

    public MonitorGen(MONITORENTER instr) {
        opcode = instr.getOpcode();
    }

    public MonitorGen(MONITOREXIT instr) {
        opcode = instr.getOpcode();
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
    }
}
