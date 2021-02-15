package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.GotoInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class GOTO_W extends GotoInstruction {
    public GOTO_W() {
    }

    public GOTO_W(InstructionHandle target) {
        super(InstructionOpCodes.GOTO_W, target);
        super.setLength(5);
    }
//    @Override
//    public void accept(final Visitor v) {
//        v.visitUnconditionalBranch(this);
//        v.visitBranchInstruction(this);
//        v.visitGotoInstruction(this);
//        v.visitGOTO_W(this);
//    }
//
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        super.setIndex(getTargetOffset());
//        out.writeByte(super.getOpcode().getOpcode());
//        out.writeInt(super.getIndex());
//    }
//
//    @Override
//    protected void initFromFile(final ByteSequence bytes, final boolean wide) {// throws IOException {
//        super.setIndex(bytes.readInt());
//        super.setLength(5);
//    }
}
