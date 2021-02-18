package com.wade.decompiler.generic.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.wade.decompiler.util.Utility;

public class InstructionHandle {
    private InstructionHandle next;
    private InstructionHandle prev;
    private Instruction instruction;
    protected int i_position = -1;
    private Set<InstructionTargeter> targeters;
    private Map<Object, Object> attributes;

    public InstructionHandle(Instruction i) {
        setInstruction(i);
    }

    public void addAttribute(Object key, Object attr) {
        if (attributes == null) {
            attributes = new HashMap<>(3);
        }
        attributes.put(key, attr);
    }

    @Deprecated
    protected void addHandle() {
        // noop
    }

    public void addTargeter(InstructionTargeter t) {
        if (targeters == null) {
            targeters = new HashSet<>();
        }
        // if(!targeters.contains(t))
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

    public Object getAttribute(Object key) {
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

    public Instruction getInstruction() {
        return instruction;
    }

    public InstructionHandle getNext() {
        return next;
    }

    public int getPosition() {
        return i_position;
    }

    public InstructionHandle getPrev() {
        return prev;
    }

    public InstructionTargeter[] getTargeters() {
        if (!hasTargeters()) {
            return new InstructionTargeter[0];
        }
        InstructionTargeter[] t = new InstructionTargeter[targeters.size()];
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

    public void removeAttribute(Object key) {
        if (attributes != null) {
            attributes.remove(key);
        }
    }

    public void removeTargeter(InstructionTargeter t) {
        if (targeters != null) {
            targeters.remove(t);
        }
    }

    public void setInstruction(Instruction i) { // Overridden in BranchHandle TODO could be package-protected?
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

    public InstructionHandle setNext(InstructionHandle next) {
        this.next = next;
        return next;
    }

    public void setPosition(int pos) {
        i_position = pos;
    }

    InstructionHandle setPrev(InstructionHandle prev) {
        this.prev = prev;
        return prev;
    }

    // See BCEL-273
    // TODO remove this method in any redesign of BCEL
    public Instruction swapInstruction(Instruction i) {
        Instruction oldInstruction = instruction;
        instruction = i;
        return oldInstruction;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean verbose) {
        return Utility.format(i_position, 4, false, ' ') + ": " + instruction.toString(verbose);
    }

    protected int updatePosition(int offset, int max_offset) {
        i_position += offset;
        return 0;
    }

    static InstructionHandle getInstructionHandle(Instruction i) {
        return new InstructionHandle(i);
    }
}
