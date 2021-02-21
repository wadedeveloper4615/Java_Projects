package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ICONST extends Instruction implements ConstantPushInstruction {
    private int value;

    public ICONST() {
    }

    public ICONST(int i, ConstantPool cp) {
        super(InstructionOpCodes.ICONST_0, 1, cp);
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
