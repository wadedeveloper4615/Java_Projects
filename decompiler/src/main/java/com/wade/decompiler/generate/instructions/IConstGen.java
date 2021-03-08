package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ICONST;
import com.wade.decompiler.classfile.instructions.type.BasicType;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IConstGen extends InstructionGen {
    private Number value;
    private BasicType type;

    public IConstGen(ICONST instr) {
        value = instr.getValue();
        type = Type.INT;
    }
}
