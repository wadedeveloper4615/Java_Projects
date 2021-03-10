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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LoadGen extends InstructionGen {
    private LocalVariableGen localVariableReference;
    private InstructionOpCodes opcode;
    private Type type;

    public LoadGen(int offset, ALOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index >= 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index];
        }
        type = Type.OBJECT;
    }

    public LoadGen(int offset, DLOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index];
        }
        if (index >= instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.DOUBLE;
    }

    public LoadGen(int offset, FLOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex()];
        type = Type.FLOAT;
    }

    public LoadGen(int offset, ILOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index];
        }
        type = Type.INTEGER;
    }

    public LoadGen(int offset, LLOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.LONG;
    }
}
