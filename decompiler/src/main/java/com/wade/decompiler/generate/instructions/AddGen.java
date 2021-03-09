package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DADD;
import com.wade.decompiler.classfile.instructions.FADD;
import com.wade.decompiler.classfile.instructions.IADD;
import com.wade.decompiler.classfile.instructions.LADD;
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
public class AddGen extends InstructionGen {
    private Type type;

    public AddGen(DADD instr) {
        type = Type.DOUBLE;
    }

    public AddGen(FADD instr) {
        type = Type.FLOAT;
    }

    public AddGen(IADD instr) {
        type = Type.INT;
    }

    public AddGen(LADD instr) {
        type = Type.LONG;
    }
}
