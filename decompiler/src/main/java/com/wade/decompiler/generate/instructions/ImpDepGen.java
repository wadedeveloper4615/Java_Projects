package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IMPDEP1;
import com.wade.decompiler.classfile.instructions.IMPDEP2;
import com.wade.decompiler.decompiler.ExpressionStack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ImpDepGen extends InstructionGen {
    public ImpDepGen(int offset, IMPDEP1 instr) {
        super(offset, instr.getLength());
    }

    public ImpDepGen(int offset, IMPDEP2 instr) {
        super(offset, instr.getLength());
    }

    @Override
    public String decompile(ExpressionStack stack) {
        return null;
    }
}
