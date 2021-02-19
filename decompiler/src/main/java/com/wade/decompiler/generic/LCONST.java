package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ClassGenException;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.type.Type;

public class LCONST extends Instruction implements ConstantPushInstruction {
    private long value;

    public LCONST() {
    }

    public LCONST(long l, ConstantPool cp) {
        super(InstructionOpCodes.LCONST_0, 1, cp);
        if (l == 0) {
            super.setOpcode(InstructionOpCodes.LCONST_0);
        } else if (l == 1) {
            super.setOpcode(InstructionOpCodes.LCONST_1);
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.LONG;
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }
}
