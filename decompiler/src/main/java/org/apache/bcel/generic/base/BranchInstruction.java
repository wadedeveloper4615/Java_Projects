package org.apache.bcel.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionTargeter;
import org.apache.bcel.util.ByteSequence;

public abstract class BranchInstruction extends Instruction implements InstructionTargeter {
    @Deprecated
    protected int index;
    @Deprecated
    protected InstructionHandle target;
    @Deprecated
    protected int position;

    public BranchInstruction() {
    }

    protected BranchInstruction(InstructionOpCodes opcode, final InstructionHandle target) {
        super(opcode, (short) 3);
        setTarget(target);
    }

    @Override
    public boolean containsTarget(final InstructionHandle ih) {
        return target == ih;
    }

    @Override
    public void dispose() {
        setTarget(null);
        index = -1;
        position = -1;
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        index = getTargetOffset();
        if (!isValidShort(index)) {
            throw new ClassGenException("Branch target offset too large for short: " + index);
        }
        out.writeShort(index);
    }

    public final int getIndex() {
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

    protected int getTargetOffset(final InstructionHandle _target) {
        if (_target == null) {
            throw new ClassGenException("Target of " + super.toString(true) + " is invalid null handle");
        }
        final int t = _target.getPosition();
        if (t < 0) {
            throw new ClassGenException("Invalid branch target position offset for " + super.toString(true) + ":" + t + ":" + _target);
        }
        return t - position;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setLength(3);
        index = bytes.readShort();
    }

    protected void setIndex(final int index) {
        this.index = index;
    }

    protected void setPosition(final int position) {
        this.position = position;
    }

    public void setTarget(final InstructionHandle target) {
        notifyTarget(this.target, target, this);
        this.target = target;
    }

    @Override
    public String toString(final boolean verbose) {
        final String s = super.toString(verbose);
        String t = "null";
        if (verbose) {
            if (target != null) {
                if (target.getInstruction() == this) {
                    t = "<points to itself>";
                } else if (target.getInstruction() == null) {
                    t = "<null instruction!!!?>";
                } else {
                    t = "" + target.getPosition();
                }
            }
        } else if (target != null) {
            index = target.getPosition();
            t = "" + index;
        }
        return s + " -> " + t;
    }

    protected int updatePosition(final int offset, final int max_offset) {
        position += offset;
        return 0;
    }

    @Override
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        if (target == old_ih) {
            setTarget(new_ih);
        } else {
            throw new ClassGenException("Not targeting " + old_ih + ", but " + target);
        }
    }

    public static void notifyTarget(final InstructionHandle old_ih, final InstructionHandle new_ih, final InstructionTargeter t) {
        if (old_ih != null) {
            old_ih.removeTargeter(t);
        }
        if (new_ih != null) {
            new_ih.addTargeter(t);
        }
    }
}
