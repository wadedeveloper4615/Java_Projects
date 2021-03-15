package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DNEG;
import com.wade.decompiler.classfile.instructions.FNEG;
import com.wade.decompiler.classfile.instructions.INEG;
import com.wade.decompiler.classfile.instructions.LNEG;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.ExpressionStack;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class NegGen extends InstructionGen {
    private Type type;

    public NegGen(int offset, DNEG instr) {
        super(offset, instr.getLength());
        type = Type.DOUBLE;
    }

    public NegGen(int offset, FNEG instr) {
        super(offset, instr.getLength());
        type = Type.FLOAT;
    }

    public NegGen(int offset, INEG instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }

    public NegGen(int offset, LNEG instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}