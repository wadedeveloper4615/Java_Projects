package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.BIPUSH;
import com.wade.decompiler.classfile.instructions.FCONST;
import com.wade.decompiler.classfile.instructions.ICONST;
import com.wade.decompiler.classfile.instructions.LDC;
import com.wade.decompiler.classfile.instructions.LDC2_W;
import com.wade.decompiler.classfile.instructions.SIPUSH;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConstGen extends InstructionGen {
    private Object value;
    private Type type;

    public ConstGen(BIPUSH instr) {
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(FCONST instr) {
        value = instr.getValue();
        type = Type.FLOAT;
    }

    public ConstGen(ICONST instr) {
        value = instr.getValue();
        type = Type.INT;
    }

    public ConstGen(LDC instr) {
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(LDC2_W instr) {
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(SIPUSH instr) {
        value = instr.getValue();
        type = instr.getType();
    }
}
