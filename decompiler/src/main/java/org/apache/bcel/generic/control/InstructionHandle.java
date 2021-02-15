package org.apache.bcel.generic.control;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.bcel.generic.base.BranchHandle;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.Instruction;

public class InstructionHandle {
    private InstructionHandle next;
    private InstructionHandle prev;
    private Instruction instruction;
    @Deprecated
    protected int i_position = -1;
    private Set<InstructionTargeter> targeters;
    private Map<Object, Object> attributes;

    protected InstructionHandle(final Instruction i) {
        setInstruction(i);
    }
//
//    public void accept(final Visitor v) {
//        instruction.accept(v);
//    }

    public void addAttribute(final Object key, final Object attr) {
        if (attributes == null) {
            attributes = new HashMap<>(3);
        }
        attributes.put(key, attr);
    }

    @Deprecated
    protected void addHandle() {
    }

    public void addTargeter(final InstructionTargeter t) {
        if (targeters == null) {
            targeters = new HashSet<>();
        }
        targeters.add(t);
    }

    void dispose() {
        next = prev = null;
        instruction.dispose();
        instruction = null;
        i_position = -1;
        attributes = null;
        removeAllTargeters();
    }

    public Object getAttribute(final Object key) {
        if (attributes != null) {
            return attributes.get(key);
        }
        return null;
    }

    public Collection<Object> getAttributes() {
        if (attributes == null) {
            attributes = new HashMap<>(3);
        }
        return attributes.values();
    }

    public final Instruction getInstruction() {
        return instruction;
    }

    public final InstructionHandle getNext() {
        return next;
    }

    public int getPosition() {
        return i_position;
    }

    public final InstructionHandle getPrev() {
        return prev;
    }

    public InstructionTargeter[] getTargeters() {
        if (!hasTargeters()) {
            return new InstructionTargeter[0];
        }
        final InstructionTargeter[] t = new InstructionTargeter[targeters.size()];
        targeters.toArray(t);
        return t;
    }

    public boolean hasTargeters() {
        return (targeters != null) && (targeters.size() > 0);
    }

    public void removeAllTargeters() {
        if (targeters != null) {
            targeters.clear();
        }
    }

    public void removeAttribute(final Object key) {
        if (attributes != null) {
            attributes.remove(key);
        }
    }

    public void removeTargeter(final InstructionTargeter t) {
        if (targeters != null) {
            targeters.remove(t);
        }
    }

    public void setInstruction(final Instruction i) {
        if (i == null) {
            throw new ClassGenException("Assigning null to handle");
        }
        if ((this.getClass() != BranchHandle.class) && (i instanceof BranchInstruction)) {
            throw new ClassGenException("Assigning branch instruction " + i + " to plain handle");
        }
        if (instruction != null) {
            instruction.dispose();
        }
        instruction = i;
    }

    final InstructionHandle setNext(final InstructionHandle next) {
        this.next = next;
        return next;
    }

    protected void setPosition(final int pos) {
        i_position = pos;
    }

    final InstructionHandle setPrev(final InstructionHandle prev) {
        this.prev = prev;
        return prev;
    }

    public Instruction swapInstruction(final Instruction i) {
        final Instruction oldInstruction = instruction;
        instruction = i;
        return oldInstruction;
    }
//
//    @Override
//    public String toString() {
//        return toString(true);
//    }
//
//    public String toString(final boolean verbose) {
//        return Utility.format(i_position, 4, false, ' ') + ": " + instruction.toString(verbose);
//    }

    protected int updatePosition(final int offset, final int max_offset) {
        i_position += offset;
        return 0;
    }

    static InstructionHandle getInstructionHandle(final Instruction i) {
        return new InstructionHandle(i);
    }
}
