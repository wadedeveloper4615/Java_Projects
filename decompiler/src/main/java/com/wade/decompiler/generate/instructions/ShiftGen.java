package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ISHL;
import com.wade.decompiler.classfile.instructions.ISHR;
import com.wade.decompiler.classfile.instructions.IUSHR;
import com.wade.decompiler.classfile.instructions.LSHL;
import com.wade.decompiler.classfile.instructions.LSHR;
import com.wade.decompiler.classfile.instructions.LUSHR;
import com.wade.decompiler.classfile.instructions.type.Type;
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
public class ShiftGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public ShiftGen(ISHL instr) {
        opcode = instr.getOpcode();
        type = Type.INT;
    }

    public ShiftGen(ISHR instr) {
        opcode = instr.getOpcode();
        type = Type.INT;
    }

    public ShiftGen(IUSHR instr) {
        opcode = instr.getOpcode();
        type = Type.INT;
    }

    public ShiftGen(LSHL instr) {
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ShiftGen(LSHR instr) {
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ShiftGen(LUSHR instr) {
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }
}
