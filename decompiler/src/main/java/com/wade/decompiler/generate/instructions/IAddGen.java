package com.wade.decompiler.generate.instructions;

import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.IADD;
import com.wade.decompiler.classfile.instructions.type.Type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IAddGen extends InstructionGen {
    private Type type;

    public IAddGen(IADD instr) {
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
}
