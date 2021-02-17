package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.GotoInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class GOTO_W extends GotoInstruction {
    public GOTO_W() {
    }

    public GOTO_W(final InstructionHandle target) {
        super(Const.GOTO_W, target);
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
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
