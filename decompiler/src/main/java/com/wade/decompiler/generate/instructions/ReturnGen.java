package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ARETURN;
import com.wade.decompiler.classfile.instructions.IRETURN;
import com.wade.decompiler.classfile.instructions.RETURN;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ReturnGen extends InstructionGen {
    private Type type;

    public ReturnGen(ARETURN instr) {
        type = switch (instr.getOpcode()) {
            case IRETURN -> Type.INT;
            case LRETURN -> Type.LONG;
            case FRETURN -> Type.FLOAT;
            case DRETURN -> Type.DOUBLE;
            case ARETURN -> Type.OBJECT;
            case RETURN -> Type.VOID;
            default -> Type.UNKNOWN;
        };
    }

    public ReturnGen(IRETURN instr) {
        type = switch (instr.getOpcode()) {
            case IRETURN -> Type.INT;
            case LRETURN -> Type.LONG;
            case FRETURN -> Type.FLOAT;
            case DRETURN -> Type.DOUBLE;
            case ARETURN -> Type.OBJECT;
            case RETURN -> Type.VOID;
            default -> Type.UNKNOWN;
        };
    }

    public ReturnGen(RETURN instr) {
        type = switch (instr.getOpcode()) {
            case IRETURN -> Type.INT;
            case LRETURN -> Type.LONG;
            case FRETURN -> Type.FLOAT;
            case DRETURN -> Type.DOUBLE;
            case ARETURN -> Type.OBJECT;
            case RETURN -> Type.VOID;
            default -> Type.UNKNOWN;
        };
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }
}
