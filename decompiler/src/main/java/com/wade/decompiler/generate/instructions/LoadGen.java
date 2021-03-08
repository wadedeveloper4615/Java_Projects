package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ALOAD;
import com.wade.decompiler.classfile.instructions.DLOAD;
import com.wade.decompiler.classfile.instructions.FLOAD;
import com.wade.decompiler.classfile.instructions.ILOAD;
import com.wade.decompiler.classfile.instructions.LLOAD;
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
public class LoadGen extends InstructionGen {
    private LocalVariableGen localVariableReference;
    private InstructionOpCodes opcode;
    private Type type;

    public LoadGen(ALOAD instr) {
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.OBJECT;
    }

    public LoadGen(DLOAD instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.DOUBLE;
    }

    public LoadGen(FLOAD instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex()];
        type = Type.FLOAT;
    }

    public LoadGen(ILOAD instr) {
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.INT;
    }

    public LoadGen(LLOAD instr) {
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.LONG;
    }
}
