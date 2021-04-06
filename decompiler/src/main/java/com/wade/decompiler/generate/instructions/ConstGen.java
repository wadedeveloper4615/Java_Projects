package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.*;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.Expression;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.decompiler.ExpressionType;
import com.wade.decompiler.enums.InstructionOpCodes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConstGen extends InstructionGen {
    private InstructionOpCodes opcode;
    private Object value;
    private Type type;
    private Class<?>[] exceptions;

    public ConstGen(int offset, ACONST_NULL instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = Type.NULL;
        type = Type.NULL;
    }

    public ConstGen(int offset, BIPUSH instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(int offset, DCONST instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = Type.DOUBLE;
    }

    public ConstGen(int offset, FCONST instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = Type.FLOAT;
    }

    public ConstGen(int offset, ICONST instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = Type.INTEGER;
    }

    public ConstGen(int offset, LCONST instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = Type.INTEGER;
    }

    public ConstGen(int offset, LDC instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(int offset, LDC2_W instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(int offset, SIPUSH instr) {
        super(offset, instr.getLength());
        opcode = instr.getOpcode();
        value = instr.getValue();
        type = instr.getType();
    }

    @Override
    public String decompile(ExpressionStack stack) {
        String name = this.value.toString();
        if (opcode == InstructionOpCodes.LDC) {
            name = "\"" + name + "\"";
        }
        stack.push(new Expression(ExpressionType.CONSTANT_NUMBER, name));
        return "pushed " + name;
    }
}
