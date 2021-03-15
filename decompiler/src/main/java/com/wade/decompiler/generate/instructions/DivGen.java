package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DDIV;
import com.wade.decompiler.classfile.instructions.FDIV;
import com.wade.decompiler.classfile.instructions.IDIV;
import com.wade.decompiler.classfile.instructions.LDIV;
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
public class DivGen extends InstructionGen {
    private Type type;

    public DivGen(int offset, DDIV instr) {
        super(offset, instr.getLength());
        type = Type.DOUBLE;
    }

    public DivGen(int offset, FDIV instr) {
        super(offset, instr.getLength());
        type = Type.FLOAT;
    }

    public DivGen(int offset, IDIV instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }

    public DivGen(int offset, LDIV instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
