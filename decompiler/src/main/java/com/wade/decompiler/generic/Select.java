package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.base.VariableLengthInstruction;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.util.ByteSequence;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {
    @Deprecated
    protected int[] match; // matches, i.e., case 1: ... TODO could be package-protected?
    @Deprecated
    protected int[] indices; // target offsets TODO could be package-protected?
    @Deprecated
    protected InstructionHandle[] targets; // target objects in instruction list TODO could be package-protected?
    @Deprecated
    protected int fixed_length; // fixed length defined by subclasses TODO could be package-protected?
    @Deprecated
    protected int match_length; // number of cases TODO could be package-protected?
    @Deprecated
    protected int padding = 0; // number of pad bytes for alignment TODO could be package-protected?

    Select() {
    }

    Select(final short opcode, final int[] match, final InstructionHandle[] targets, final InstructionHandle defaultTarget) {
        // don't set default target before instuction is built
        super(opcode, null);
        this.match = match;
        this.targets = targets;
        // now it's safe to set default target
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
        out.writeByte(super.getOpcode());
        for (int i = 0; i < padding; i++) {
            out.writeByte(0);
        }
        super.setIndex(getTargetOffset()); // Write default target offset
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
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        padding = (4 - (bytes.getIndex() % 4)) % 4; // Compute number of pad bytes
        for (int i = 0; i < padding; i++) {
            bytes.readByte();
        }
        // Default branch target common for both cases (TABLESWITCH, LOOKUPSWITCH)
        super.setIndex(bytes.readInt());
    }

    final void setFixed_length(final int fixed_length) {
        this.fixed_length = fixed_length;
    }

    final int setIndices(final int i, final int value) {
        indices[i] = value;
        return value; // Allow use in nested calls
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

    public void setTarget(final int i, final InstructionHandle target) { // TODO could be package-protected?
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
        setPosition(getPosition() + offset); // Additional offset caused by preceding SWITCHs, GOTOs, etc.
        final short old_length = (short) super.getLength();
        padding = (4 - ((getPosition() + 1) % 4)) % 4;
        super.setLength((short) (fixed_length + padding)); // Update length
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
