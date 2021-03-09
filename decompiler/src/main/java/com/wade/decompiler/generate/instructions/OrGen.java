package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.IOR;
import com.wade.decompiler.classfile.instructions.LOR;
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
public class OrGen extends InstructionGen {
    private Type type;

    public OrGen(IOR instr) {
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

    public OrGen(LOR instr) {
        type = Type.LONG;
    }
}
