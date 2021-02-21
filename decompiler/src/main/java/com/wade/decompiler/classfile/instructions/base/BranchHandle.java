package com.wade.decompiler.classfile.instructions.base;

public class BranchHandle extends InstructionHandle {
    private BranchInstruction bi;

    private BranchHandle(BranchInstruction i) {
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
    public void setInstruction(Instruction i) {
        super.setInstruction(i);
        if (!(i instanceof BranchInstruction)) {
            throw new ClassGenException("Assigning " + i + " to branch handle which is not a branch instruction");
        }
        bi = (BranchInstruction) i;
    }

    @Override
    public void setPosition(int pos) {
        bi.setPosition(pos);
        super.setPosition(pos);
    }

    public void setTarget(InstructionHandle ih) {
        bi.setTarget(ih);
    }

    @Override
    protected int updatePosition(int offset, int max_offset) {
        int x = bi.updatePosition(offset, max_offset);
        super.setPosition(bi.getPosition());
        return x;
    }

    public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
        bi.updateTarget(old_ih, new_ih);
    }

    public static BranchHandle getBranchHandle(BranchInstruction i) {
        return new BranchHandle(i);
    }
}
