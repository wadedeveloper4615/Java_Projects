package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FCONST extends Instruction implements ConstantPushInstruction {
    private float value;

    public FCONST() {
    }

    public FCONST(float f, ConstantPool cp) {
        super(InstructionOpCodes.FCONST_0, 1, cp);
        if (f == 0.0) {
            super.setOpcode(InstructionOpCodes.FCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(InstructionOpCodes.FCONST_1);
        } else if (f == 2.0) {
            super.setOpcode(InstructionOpCodes.FCONST_2);
        } else {
            throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
        }
        value = f;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.FLOAT;
    }

    @Override
    public Number getValue() {
        return Float.valueOf(value);
    }
}
