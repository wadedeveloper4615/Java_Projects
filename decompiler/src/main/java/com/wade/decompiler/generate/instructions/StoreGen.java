package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
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
    private InstructionOpCodes opcode;
    private Type type;
    private LocalVariableGen localVariableReference;

    public StoreGen(int offset, ASTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index];
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
            localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[index];
        }
        type = Type.INTEGER;
    }

    public StoreGen(int offset, LSTORE instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        localVariableReference = instr.getLocalVariableTable().getLocalVariableTable()[instr.getIndex() - 1];
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        if (!stack.empty()) {
            Expression var = stack.pop();
            return this.localVariableReference.getName() + " = " + var.getValue();
        }
        return "empty stack";
    }
}
