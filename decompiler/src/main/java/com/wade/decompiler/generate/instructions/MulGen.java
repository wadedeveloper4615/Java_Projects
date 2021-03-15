package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DMUL;
import com.wade.decompiler.classfile.instructions.FMUL;
import com.wade.decompiler.classfile.instructions.IMUL;
import com.wade.decompiler.classfile.instructions.LMUL;
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
public class MulGen extends InstructionGen {
    private Type type;

    public MulGen(int offset, DMUL instr) {
        super(offset, instr.getLength());
        type = Type.DOUBLE;
    }

    public MulGen(int offset, FMUL instr) {
        super(offset, instr.getLength());
        type = Type.FLOAT;
    }

    public MulGen(int offset, IMUL instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }

    public MulGen(int offset, LMUL instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
