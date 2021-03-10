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
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StoreGen extends InstructionGen {
    private LocalVariableGen localVariableReference;
    private InstructionOpCodes opcode;
    private Type type;

    public StoreGen(int offset, ASTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.OBJECT;
    }

    public StoreGen(int offset, DSTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.DOUBLE;
    }

    public StoreGen(int offset, FSTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex()];
        type = Type.FLOAT;
    }

    public StoreGen(int offset, ISTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        if (index > 0 && index < instr.getLocalVariableTable().getLocalVariableTable().length) {
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index - 1];
        }
        type = Type.INT;
    }

    public StoreGen(int offset, LSTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.LONG;
    }
}
