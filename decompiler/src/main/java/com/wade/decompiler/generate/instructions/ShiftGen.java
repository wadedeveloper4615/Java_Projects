package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ISHL;
import com.wade.decompiler.classfile.instructions.ISHR;
import com.wade.decompiler.classfile.instructions.IUSHR;
import com.wade.decompiler.classfile.instructions.LSHL;
import com.wade.decompiler.classfile.instructions.LSHR;
import com.wade.decompiler.classfile.instructions.LUSHR;
import com.wade.decompiler.classfile.instructions.type.Type;
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
public class ShiftGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public ShiftGen(int offset, ISHL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ShiftGen(int offset, ISHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ShiftGen(int offset, IUSHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
    }

    public ShiftGen(int offset, LSHL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ShiftGen(int offset, LSHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ShiftGen(int offset, LUSHR instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }
}
