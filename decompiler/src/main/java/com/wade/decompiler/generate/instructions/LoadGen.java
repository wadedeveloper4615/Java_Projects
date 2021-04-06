package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
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
        localVariableReference = findLocalVariable(instr.getLocalVariableTable().getLocalVariableTable(), index);
        type = Type.OBJECT;
    }

    public LoadGen(int offset, DLOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        localVariableReference = findLocalVariable(instr.getLocalVariableTable().getLocalVariableTable(), index);
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
        localVariableReference = findLocalVariable(instr.getLocalVariableTable().getLocalVariableTable(), index);
        type = Type.INTEGER;
    }

    public LoadGen(int offset, LLOAD instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        int index = instr.getIndex();
        localVariableReference = findLocalVariable(instr.getLocalVariableTable().getLocalVariableTable(), index);
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        String name = "";
        if (localVariableReference != null) {
            name = this.localVariableReference.getName();
        }
        Expression item = new Expression(ExpressionType.VARIABLE, name);
        stack.push(item);
        return "pushed " + item.toString();
    }

    private LocalVariableGen findLocalVariable(LocalVariableGen[] localVars, int index) {
        for (LocalVariableGen lv : localVars) {
            if (lv.getIndex() == index) {
                return lv;
            }
        }
        return null;
    }
}
