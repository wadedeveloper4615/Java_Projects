package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.generic.base.GotoInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.util.ByteSequence;

public class GOTO_W extends GotoInstruction {
    public GOTO_W() {
    }

    public GOTO_W(final InstructionHandle target) {
        super(org.apache.bcel.Const.GOTO_W, target);
        super.setLength(5);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitUnconditionalBranch(this);
        v.visitBranchInstruction(this);
        v.visitGotoInstruction(this);
        v.visitGOTO_W(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        out.writeByte(super.getOpcode());
        out.writeInt(super.getIndex());
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
