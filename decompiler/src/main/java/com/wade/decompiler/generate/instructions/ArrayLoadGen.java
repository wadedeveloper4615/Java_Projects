package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.AALOAD;
import com.wade.decompiler.classfile.instructions.BALOAD;
import com.wade.decompiler.classfile.instructions.CALOAD;
import com.wade.decompiler.classfile.instructions.DALOAD;
import com.wade.decompiler.classfile.instructions.FALOAD;
import com.wade.decompiler.classfile.instructions.IALOAD;
import com.wade.decompiler.classfile.instructions.LALOAD;
import com.wade.decompiler.classfile.instructions.SALOAD;
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
public class ArrayLoadGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;
    private Class<?>[] exceptions;

    public ArrayLoadGen(int offset, AALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.OBJECT;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, BALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.BYTE;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, CALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.CHAR;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, DALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, FALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.FLOAT;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, IALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.INTEGER;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, LALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.LONG;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }

    public ArrayLoadGen(int offset, SALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        type = Type.SHORT;
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }
}
