package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ClassGenException;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.type.Type;

public class DCONST extends Instruction implements ConstantPushInstruction {
    private double value;

    public DCONST() {
    }

    public DCONST(double f, ConstantPool cp) {
        super(InstructionOpCodes.DCONST_0, 1, cp);
        if (f == 0.0) {
            super.setOpcode(InstructionOpCodes.DCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(InstructionOpCodes.DCONST_1);
        } else {
            throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
        }
        value = f;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.DOUBLE;
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }
}
