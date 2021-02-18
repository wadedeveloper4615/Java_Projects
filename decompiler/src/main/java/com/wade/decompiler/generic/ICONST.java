package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ClassGenException;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.type.Type;

public class ICONST extends Instruction implements ConstantPushInstruction {
    private int value;

    public ICONST() {
    }

    public ICONST(int i) {
        super(InstructionOpCodes.ICONST_0, 1);
        if ((i >= -1) && (i <= 5)) {
            super.setOpcode(InstructionOpCodes.read((short) (InstructionOpCodes.ICONST_0.getOpcode() + i))); // Even works for i == -1
        } else {
            throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
        }
        value = i;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.INT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }
}
