package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.AASTORE;
import com.wade.decompiler.classfile.instructions.CASTORE;
import com.wade.decompiler.classfile.instructions.DASTORE;
import com.wade.decompiler.classfile.instructions.FASTORE;
import com.wade.decompiler.classfile.instructions.IASTORE;
import com.wade.decompiler.classfile.instructions.LASTORE;
import com.wade.decompiler.classfile.instructions.SASTORE;
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
public class ArrayStoreGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Type type;

    public ArrayStoreGen(AASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.OBJECT;
    }

    public ArrayStoreGen(CASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.CHAR;
    }

    public ArrayStoreGen(DASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.DOUBLE;
    }

    public ArrayStoreGen(FASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.FLOAT;
    }

    public ArrayStoreGen(IASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.INT;
    }

    public ArrayStoreGen(LASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.LONG;
    }

    public ArrayStoreGen(SASTORE instr) {
        opcode = instr.getOpcode();
        type = Type.SHORT;
    }
}
