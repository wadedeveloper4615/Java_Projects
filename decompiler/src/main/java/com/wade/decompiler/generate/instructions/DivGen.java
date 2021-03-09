package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.DDIV;
import com.wade.decompiler.classfile.instructions.FDIV;
import com.wade.decompiler.classfile.instructions.IDIV;
import com.wade.decompiler.classfile.instructions.LDIV;
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
public class DivGen extends InstructionGen {
    private Type type;

    public DivGen(DDIV instr) {
        type = Type.DOUBLE;
    }

    public DivGen(FDIV instr) {
        type = Type.FLOAT;
    }

    public DivGen(IDIV instr) {
        type = Type.INT;
    }

    public DivGen(LDIV instr) {
        type = Type.LONG;
    }
}
