package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.instructions.ACONST_NULL;
import com.wade.decompiler.classfile.instructions.BIPUSH;
import com.wade.decompiler.classfile.instructions.DCONST;
import com.wade.decompiler.classfile.instructions.FCONST;
import com.wade.decompiler.classfile.instructions.ICONST;
import com.wade.decompiler.classfile.instructions.LCONST;
import com.wade.decompiler.classfile.instructions.LDC;
import com.wade.decompiler.classfile.instructions.LDC2_W;
import com.wade.decompiler.classfile.instructions.SIPUSH;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("unused")
public class ConstGen extends InstructionGen {
    private Object value;
    private Type type;
    private Class<?>[] exceptions;

    public ConstGen(ACONST_NULL instr) {
        value = Type.NULL;
        type = Type.NULL;
    }

    public ConstGen(BIPUSH instr) {
        value = instr.getValue();
        type = instr.getType();
    }

    public ConstGen(DCONST instr) {
        value = instr.getValue();
        type = Type.DOUBLE;
    }

    public ConstGen(FCONST instr) {
        value = instr.getValue();
        type = Type.FLOAT;
    }

    public ConstGen(ICONST instr) {
        value = instr.getValue();
        type = Type.INT;
    }

    public ConstGen(LCONST instr) {
        value = instr.getValue();
        type = Type.INT;
    }

    public ConstGen(LDC instr) {
        value = instr.getValue();
        type = instr.getType();
        exceptions = ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_STRING_RESOLUTION);
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
