package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.AALOAD;
import com.wade.decompiler.classfile.instructions.CALOAD;
import com.wade.decompiler.classfile.instructions.DALOAD;
import com.wade.decompiler.classfile.instructions.FALOAD;
import com.wade.decompiler.classfile.instructions.IALOAD;
import com.wade.decompiler.classfile.instructions.LALOAD;
import com.wade.decompiler.classfile.instructions.SALOAD;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ArrayLoadGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public ArrayLoadGen(AALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.OBJECT;
    }

    public ArrayLoadGen(CALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.CHAR;
    }

    public ArrayLoadGen(DALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArrayLoadGen(FALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArrayLoadGen(IALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.INT;
    }

    public ArrayLoadGen(LALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArrayLoadGen(SALOAD instr) {
        opcode = instr.getOpcode();
        type = Type.SHORT;
    }
}
