package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

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
