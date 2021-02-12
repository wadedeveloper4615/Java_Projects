package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.GotoInstruction;
import org.apache.bcel.generic.base.VariableLengthInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;

public class GOTO extends GotoInstruction implements VariableLengthInstruction {
    public GOTO() {
    }

    public GOTO(final InstructionHandle target) {
        super(InstructionOpCodes.GOTO, target);
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
        InstructionOpCodes _opcode = getOpcode();
        if (_opcode == InstructionOpCodes.GOTO) {
            super.dump(out);
        } else {
            super.setIndex(getTargetOffset());
            out.writeByte(_opcode.getOpcode());
            out.writeInt(super.getIndex());
        }
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        final int i = getTargetOffset();
        setPosition(getPosition() + offset);
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) {
            super.setOpcode(InstructionOpCodes.GOTO_W);
            final short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
