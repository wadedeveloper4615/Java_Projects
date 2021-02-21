package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.BranchInstruction;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.base.VariableLengthInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {
    protected int[] match;
    protected int[] indices;
    protected InstructionHandle[] targets;
    protected int fixed_length;
    protected int match_length;
    protected int padding = 0;

    public Select() {
    }

    public Select(InstructionOpCodes opcode, int[] match, InstructionHandle[] targets, InstructionHandle defaultTarget, ConstantPool cp) {
        // don't set default target before instuction is built
        super(opcode, null, cp);
        this.match = match;
        this.targets = targets;
        // now it's safe to set default target
        setTarget(defaultTarget);
        for (InstructionHandle target2 : targets) {
            notifyTarget(null, target2, this);
        }
        if ((match_length = match.length) != targets.length) {
            throw new ClassGenException("Match and target array have not the same length: Match length: " + match.length + " Target length: " + targets.length);
        }
        indices = new int[match_length];
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Select copy = (Select) super.clone();
        copy.match = match.clone();
        copy.indices = indices.clone();
        copy.targets = targets.clone();
        return copy;
    }

    @Override
    public boolean containsTarget(InstructionHandle ih) {
        if (super.getTarget() == ih) {
            return true;
        }
        for (InstructionHandle target2 : targets) {
            if (target2 == ih) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispose() {
        super.dispose();
        for (InstructionHandle target2 : targets) {
            target2.removeTargeter(this);
        }
    }

    int getFixed_length() {
        return fixed_length;
    }

    public int[] getIndices() {
        return indices;
    }

    int getIndices(int index) {
        return indices[index];
    }

    int getMatch(int index) {
        return match[index];
    }

    int getMatch_length() {
        return match_length;
    }

    public int[] getMatchs() {
        return match;
    }

    int getPadding() {
        return padding;
    }

    InstructionHandle getTarget(int index) {
        return targets[index];
    }

    public InstructionHandle[] getTargets() {
        return targets;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        padding = (4 - (bytes.getIndex() % 4)) % 4; // Compute number of pad bytes
        for (int i = 0; i < padding; i++) {
            bytes.readByte();
        }
        // Default branch target common for both cases (TABLESWITCH, LOOKUPSWITCH)
        super.setIndex(bytes.readInt());
    }

    void setFixed_length(int fixed_length) {
        this.fixed_length = fixed_length;
    }

    int setIndices(int i, int value) {
        indices[i] = value;
        return value; // Allow use in nested calls
    }

    void setIndices(int[] array) {
        indices = array;
    }

    void setMatch(int index, int value) {
        match[index] = value;
    }

    int setMatch_length(int match_length) {
        this.match_length = match_length;
        return match_length;
    }

    void setMatches(int[] array) {
        match = array;
    }

    public void setTarget(int i, InstructionHandle target) { // TODO could be package-protected?
        notifyTarget(targets[i], target, this);
        targets[i] = target;
    }

    void setTargets(InstructionHandle[] array) {
        targets = array;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(super.toString());
        for (int i = 0; i < match_length; i++) {
            String s = "null";
            if (targets[i] != null) {
                s = targets[i].getInstruction().toString();
            }
            buf.append("(").append(match[i]).append(", ").append(s).append(" = {").append(indices[i]).append("})");
        }
        return buf.toString();
    }

    @Override
    protected int updatePosition(int offset, int max_offset) {
        setPosition(getPosition() + offset); // Additional offset caused by preceding SWITCHs, GOTOs, etc.
        short old_length = (short) super.getLength();
        padding = (4 - ((getPosition() + 1) % 4)) % 4;
        super.setLength((short) (fixed_length + padding)); // Update length
        return super.getLength() - old_length;
    }

    @Override
    public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
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
