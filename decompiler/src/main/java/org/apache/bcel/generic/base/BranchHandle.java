package org.apache.bcel.generic.base;

import org.apache.bcel.generic.control.InstructionHandle;
//import org.apache.bcel.generic.control.InstructionList;

public final class BranchHandle extends InstructionHandle {
    private BranchInstruction bi;

    private BranchHandle(final BranchInstruction i) {
        super(i);
        bi = i;
    }

    @Override
    public int getPosition() {
        return bi.getPosition();
    }

    public InstructionHandle getTarget() {
        return bi.getTarget();
    }

    @Override
    public void setInstruction(final Instruction i) {
        super.setInstruction(i);
        if (!(i instanceof BranchInstruction)) {
            throw new ClassGenException("Assigning " + i + " to branch handle which is not a branch instruction");
        }
        bi = (BranchInstruction) i;
    }

    @Override
    protected void setPosition(final int pos) {
        bi.setPosition(pos);
        super.setPosition(pos);
    }

    public void setTarget(final InstructionHandle ih) {
        bi.setTarget(ih);
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        final int x = bi.updatePosition(offset, max_offset);
        super.setPosition(bi.getPosition());
        return x;
    }

//
//    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
//        bi.updateTarget(old_ih, new_ih);
//    }
//
    public static BranchHandle getBranchHandle(final BranchInstruction i) {
        return new BranchHandle(i);
    }
}
