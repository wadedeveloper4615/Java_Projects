package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.GotoInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.VariableLengthInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class GOTO extends GotoInstruction implements VariableLengthInstruction {
    public GOTO() {
    }

    public GOTO(InstructionHandle target) {
        super(InstructionOpCodes.GOTO, target);
    }

    @Override
    public void accept(Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitUnconditionalBranch(this);
        v.visitBranchInstruction(this);
        v.visitGotoInstruction(this);
        v.visitGOTO(this);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        InstructionOpCodes _opcode = getOpcode();
        if (_opcode == InstructionOpCodes.GOTO) {
            super.dump(out);
        } else { // GOTO_W
            super.setIndex(getTargetOffset());
            out.writeByte(_opcode.getOpcode());
            out.writeInt(super.getIndex());
        }
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
