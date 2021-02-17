package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.GotoInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.VariableLengthInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class GOTO extends GotoInstruction implements VariableLengthInstruction {
    public GOTO() {
    }

    public GOTO(final InstructionHandle target) {
        super(Const.GOTO, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitVariableLengthInstruction(this);
        v.visitUnconditionalBranch(this);
        v.visitBranchInstruction(this);
        v.visitGotoInstruction(this);
        v.visitGOTO(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        final short _opcode = getOpcode();
        if (_opcode == Const.GOTO) {
            super.dump(out);
        } else { // GOTO_W
            super.setIndex(getTargetOffset());
            out.writeByte(_opcode);
            out.writeInt(super.getIndex());
        }
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        final int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) { // to large for short (estimate)
            super.setOpcode(Const.GOTO_W);
            final short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
