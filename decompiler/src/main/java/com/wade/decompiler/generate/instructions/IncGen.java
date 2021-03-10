package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IINC;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IncGen extends InstructionGen {
    private Type type;

    public IncGen(int offset, IINC instr) {
        super(offset, instr.getLength());
        type = Type.INTEGER;
    }
}
