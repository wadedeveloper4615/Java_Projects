package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

public abstract class BranchInstruction extends Instruction implements InstructionTargeter {
    protected int index;
    protected InstructionHandle target;
    protected int position;

    public BranchInstruction() {
    }

    public BranchInstruction(InstructionOpCodes opcode, InstructionHandle target) {
        super(opcode, 3);
        setTarget(target);
    }

    @Override
    public boolean containsTarget(InstructionHandle ih) {
        return target == ih;
    }

    @Override
    public void dispose() {
        setTarget(null);
        index = -1;
        position = -1;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        index = getTargetOffset();
        if (!isValidShort(index)) {
            throw new ClassGenException("Branch target offset too large for short: " + index);
        }
        out.writeShort(index); // May be negative, i.e., point backwards
    }

    public int getIndex() {
        return index;
    }

    public int getPosition() {
        return position;
    }

    public InstructionHandle getTarget() {
        return target;
    }

    protected int getTargetOffset() {
        return getTargetOffset(target);
    }

    protected int getTargetOffset(InstructionHandle _target) {
        if (_target == null) {
            throw new ClassGenException("Target of " + super.toString(true) + " is invalid null handle");
        }
        int t = _target.getPosition();
        if (t < 0) {
            throw new ClassGenException("Invalid branch target position offset for " + super.toString(true) + ":" + t + ":" + _target);
        }
        return t - position;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(3);
        index = bytes.readShort();
    }

    protected void setIndex(int index) {
        this.index = index;
    }

    protected void setPosition(int position) {
        this.position = position;
    }

    public void setTarget(InstructionHandle target) {
        notifyTarget(this.target, target, this);
        this.target = target;
    }

    @Override
    public String toString(boolean verbose) {
        String s = super.toString(verbose);
        String t = "null";
        if (verbose) {
            if (target != null) {
                if (target.getInstruction() == this) {
                    t = "<points to itself>";
                } else if (target.getInstruction() == null) {
                    t = "<null instruction!!!?>";
                } else {
                    // I'm more interested in the address of the target then
                    // the instruction located there.
                    // t = target.getInstruction().toString(false); // Avoid circles
                    t = "" + target.getPosition();
                }
            }
        } else if (target != null) {
            index = target.getPosition();
            // index = getTargetOffset(); crashes if positions haven't been set
            // t = "" + (index + position);
            t = "" + index;
        }
        return s + " -> " + t;
    }

    protected int updatePosition(int offset, int max_offset) {
        position += offset;
        return 0;
    }

    @Override
    public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
        if (target == old_ih) {
            setTarget(new_ih);
        } else {
            throw new ClassGenException("Not targeting " + old_ih + ", but " + target);
        }
    }

    public static void notifyTarget(InstructionHandle old_ih, InstructionHandle new_ih, InstructionTargeter t) {
        if (old_ih != null) {
            old_ih.removeTargeter(t);
        }
        if (new_ih != null) {
            new_ih.addTargeter(t);
        }
    }
}
