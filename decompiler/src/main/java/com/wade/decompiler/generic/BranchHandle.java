package com.wade.decompiler.generic;

public final class BranchHandle extends InstructionHandle {
    // This is also a cache in case the InstructionHandle#swapInstruction() method
    // is used
    // See BCEL-273
    private BranchInstruction bi; // An alias in fact, but saves lots of casts

    private BranchHandle(final BranchInstruction i) {
        super(i);
        bi = i;
    }

    static BranchHandle getBranchHandle(final BranchInstruction i) {
        return new BranchHandle(i);
    }

    @Override
    public int getPosition() {
        return bi.getPosition();
    }

    @Override
    void setPosition(final int pos) {
        // Original code: i_position = bi.position = pos;
        bi.setPosition(pos);
        super.setPosition(pos);
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        final int x = bi.updatePosition(offset, max_offset);
        super.setPosition(bi.getPosition());
        return x;
    }

    public void setTarget(final InstructionHandle ih) {
        bi.setTarget(ih);
    }

    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        bi.updateTarget(old_ih, new_ih);
    }

    public InstructionHandle getTarget() {
        return bi.getTarget();
    }

    @Override // This is only done in order to apply the additional type check; could be
    // merged with super impl.
    public void setInstruction(final Instruction i) { // TODO could be package-protected?
        super.setInstruction(i);
        if (!(i instanceof BranchInstruction)) {
            throw new ClassGenException("Assigning " + i + " to branch handle which is not a branch instruction");
        }
        bi = (BranchInstruction) i;
    }
}
