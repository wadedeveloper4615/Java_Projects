package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.StackConsumer;
import org.apache.bcel.generic.base.VariableLengthInstruction;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.util.ByteSequence;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {
    protected int[] match;
    protected int[] indices;
    protected InstructionHandle[] targets;
    protected int fixed_length;
    protected int match_length;
    protected int padding = 0;

    public Select() {
    }

    Select(InstructionOpCodes opcode, final int[] match, final InstructionHandle[] targets, final InstructionHandle defaultTarget) {
        super(opcode, null);
        this.match = match;
        this.targets = targets;
        setTarget(defaultTarget);
        for (final InstructionHandle target2 : targets) {
            notifyTarget(null, target2, this);
        }
        if ((match_length = match.length) != targets.length) {
            throw new ClassGenException("Match and target array have not the same length: Match length: " + match.length + " Target length: " + targets.length);
        }
        indices = new int[match_length];
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        final Select copy = (Select) super.clone();
        copy.match = match.clone();
        copy.indices = indices.clone();
        copy.targets = targets.clone();
        return copy;
    }

    @Override
    public boolean containsTarget(final InstructionHandle ih) {
        if (super.getTarget() == ih) {
            return true;
        }
        for (final InstructionHandle target2 : targets) {
            if (target2 == ih) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispose() {
        super.dispose();
        for (final InstructionHandle target2 : targets) {
            target2.removeTargeter(this);
        }
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        for (int i = 0; i < padding; i++) {
            out.writeByte(0);
        }
        super.setIndex(getTargetOffset());
        out.writeInt(super.getIndex());
    }

    final int getFixed_length() {
        return fixed_length;
    }

    public int[] getIndices() {
        return indices;
    }

    final int getIndices(final int index) {
        return indices[index];
    }

    final int getMatch(final int index) {
        return match[index];
    }

    final int getMatch_length() {
        return match_length;
    }

    public int[] getMatchs() {
        return match;
    }

    final int getPadding() {
        return padding;
    }

    final InstructionHandle getTarget(final int index) {
        return targets[index];
    }

    public InstructionHandle[] getTargets() {
        return targets;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        padding = (4 - (bytes.getIndex() % 4)) % 4;
        for (int i = 0; i < padding; i++) {
            bytes.readByte();
        }
        super.setIndex(bytes.readInt());
    }

    final void setFixed_length(final int fixed_length) {
        this.fixed_length = fixed_length;
    }

    final int setIndices(final int i, final int value) {
        indices[i] = value;
        return value;
    }

    final void setIndices(final int[] array) {
        indices = array;
    }

    final void setMatch(final int index, final int value) {
        match[index] = value;
    }

    final int setMatch_length(final int match_length) {
        this.match_length = match_length;
        return match_length;
    }

    final void setMatches(final int[] array) {
        match = array;
    }

    public void setTarget(final int i, final InstructionHandle target) {
        notifyTarget(targets[i], target, this);
        targets[i] = target;
    }

    final void setTargets(final InstructionHandle[] array) {
        targets = array;
    }

    @Override
    public String toString(final boolean verbose) {
        final StringBuilder buf = new StringBuilder(super.toString(verbose));
        if (verbose) {
            for (int i = 0; i < match_length; i++) {
                String s = "null";
                if (targets[i] != null) {
                    s = targets[i].getInstruction().toString();
                }
                buf.append("(").append(match[i]).append(", ").append(s).append(" = {").append(indices[i]).append("})");
            }
        } else {
            buf.append(" ...");
        }
        return buf.toString();
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        setPosition(getPosition() + offset);
        final short old_length = (short) super.getLength();
        padding = (4 - ((getPosition() + 1) % 4)) % 4;
        super.setLength((short) (fixed_length + padding));
        return super.getLength() - old_length;
    }

    @Override
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        boolean targeted = false;
        if (super.getTarget() == old_ih) {
            targeted = true;
            setTarget(new_ih);
        }
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] == old_ih) {
                targeted = true;
                setTarget(i, new_ih);
            }
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih);
        }
    }
}
