package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.ReturnaddressType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.enums.TypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
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

    @Override
    public String decompile(ExpressionStack stack) {
        if (type.getType() != TypeEnum.T_VOID) {
            boolean isEmpty = stack.isEmpty();
            if (!isEmpty) {
                Expression item1 = stack.pop();
                return "return " + item1.getValue();
            }
        }
        return null;
    }

    public Type getType() {
        return switch (opcode) {
            case IRETURN -> Type.INTEGER;
            case LRETURN -> Type.LONG;
            case FRETURN -> Type.FLOAT;
            case DRETURN -> Type.DOUBLE;
            case ARETURN -> Type.OBJECT;
            case RETURN -> Type.VOID;
            default -> Type.UNKNOWN;
        };
    }
}
