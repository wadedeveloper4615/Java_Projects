package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ASTORE;
import com.wade.decompiler.classfile.instructions.DSTORE;
import com.wade.decompiler.classfile.instructions.FSTORE;
import com.wade.decompiler.classfile.instructions.ISTORE;
import com.wade.decompiler.classfile.instructions.LSTORE;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StoreGen extends InstructionGen {
    private LocalVariableGen localVariableReference;
    private InstructionOpCodes opcode;
    private Type type;

    public StoreGen(ASTORE instr) {
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.OBJECT;
    }

    public StoreGen(DSTORE instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.DOUBLE;
    }

    public StoreGen(FSTORE instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex()];
        type = Type.FLOAT;
    }

    public StoreGen(ISTORE instr) {
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.INT;
    }

    public StoreGen(LSTORE instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.LONG;
    }
}
