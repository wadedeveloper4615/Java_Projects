package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.IAND;
import com.wade.decompiler.classfile.instructions.LAND;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class AndGen extends InstructionGen {
    private Type type;

    public AndGen(IAND instr) {
        type = Type.INT;
    }

    public AndGen(LAND instr) {
        type = Type.LONG;
    }
}
