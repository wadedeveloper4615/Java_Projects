package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IXOR;
import com.wade.decompiler.classfile.instructions.LXOR;
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
public class XOrGen extends InstructionGen {
    private Type type;

    public XOrGen(int offset, IXOR instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }

    public XOrGen(int offset, LXOR instr) {
        super(offset, instr.getLength());
        type = Type.LONG;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}