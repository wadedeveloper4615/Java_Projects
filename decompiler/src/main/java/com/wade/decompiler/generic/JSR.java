package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.JsrInstruction;
import com.wade.decompiler.generic.base.VariableLengthInstruction;

public class JSR extends JsrInstruction implements VariableLengthInstruction {
    public JSR() {
    }

    public JSR(InstructionHandle target) {
        super(InstructionOpCodes.JSR, target);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        if (super.getOpcode() == InstructionOpCodes.JSR) {
            super.dump(out);
        } else { // JSR_W
            super.setIndex(getTargetOffset());
            out.writeByte(super.getOpcode().getOpcode());
            out.writeInt(super.getIndex());
        }
    }

    @Override
    protected int updatePosition(int offset, int max_offset) {
        int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) { // to large for short (estimate)
            super.setOpcode(InstructionOpCodes.JSR_W);
            short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
