package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ARETURN;
import com.wade.decompiler.classfile.instructions.DRETURN;
import com.wade.decompiler.classfile.instructions.FRETURN;
import com.wade.decompiler.classfile.instructions.IRETURN;
import com.wade.decompiler.classfile.instructions.LRETURN;
import com.wade.decompiler.classfile.instructions.RET;
import com.wade.decompiler.classfile.instructions.RETURN;
import com.wade.decompiler.classfile.instructions.type.ReturnaddressType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class ReturnGen extends InstructionGen {
    private Type type;
    private InstructionOpCodes opcode;

    public ReturnGen(int offset, ARETURN instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = getType();
    }

    public ReturnGen(int offset, DRETURN instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = getType();
    }

    public ReturnGen(int offset, FRETURN instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = getType();
    }

    public ReturnGen(int offset, IRETURN instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = getType();
    }

    public ReturnGen(int offset, LRETURN instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = getType();
    }

    public ReturnGen(int offset, RET instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = ReturnaddressType.NO_TARGET;
    }

    public ReturnGen(int offset, RETURN instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = getType();
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }

    public Type getType() {
        return switch (opcode) {
            case IRETURN -> Type.INT;
            case LRETURN -> Type.LONG;
            case FRETURN -> Type.FLOAT;
            case DRETURN -> Type.DOUBLE;
            case ARETURN -> Type.OBJECT;
            case RETURN -> Type.VOID;
            default -> Type.UNKNOWN;
        };
    }
}
