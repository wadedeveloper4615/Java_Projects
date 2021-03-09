package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.DREM;
import com.wade.decompiler.classfile.instructions.FREM;
import com.wade.decompiler.classfile.instructions.IREM;
import com.wade.decompiler.classfile.instructions.LREM;
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
public class RemGen extends InstructionGen {
    private Type type;

    public RemGen(DREM instr) {
        type = Type.DOUBLE;
    }

    public RemGen(FREM instr) {
        type = Type.FLOAT;
    }

    public RemGen(IREM instr) {
        type = switch (instr.getOpcode()) {
            case DADD, DDIV, DMUL, DNEG, DREM, DSUB -> Type.DOUBLE;
            case FADD, FDIV, FMUL, FNEG, FREM, FSUB -> Type.FLOAT;
            case IADD, IAND, IDIV, IMUL, INEG, IOR, IREM, ISHL, ISHR, ISUB, IUSHR, IXOR -> Type.INT;
            case LADD, LAND, LDIV, LMUL, LNEG, LOR, LREM, LSHL, LSHR, LSUB, LUSHR, LXOR -> Type.LONG;
            default -> {
                throw new ClassGenException("Unknown type " + instr.getOpcode());
            }
        };
    }

    public RemGen(LREM instr) {
        type = Type.LONG;
    }
}
