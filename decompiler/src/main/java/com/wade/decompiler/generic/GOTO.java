package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.GotoInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.VariableLengthInstruction;

public class GOTO extends GotoInstruction implements VariableLengthInstruction {
    public GOTO() {
    }

    public GOTO(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.GOTO, target, cp);
    }

    @Override
    protected int updatePosition(int offset, int max_offset) {
        int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) { // to large for short (estimate)
            super.setOpcode(InstructionOpCodes.GOTO_W);
            short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
