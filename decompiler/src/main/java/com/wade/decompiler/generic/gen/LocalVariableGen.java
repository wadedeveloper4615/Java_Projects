package com.wade.decompiler.generic.gen;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.LocalVariable;
import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.InstructionTargeter;
import com.wade.decompiler.generic.base.NamedAndTyped;
import com.wade.decompiler.generic.base.Type;

public class LocalVariableGen implements InstructionTargeter, NamedAndTyped, Cloneable {
    private int index;
    private String name;
    private Type type;
    private InstructionHandle start;
    private InstructionHandle end;
    private int origIndex; // never changes; used to match up with LocalVariableTypeTable entries
    private boolean liveToEnd;

    public LocalVariableGen(final int index, final String name, final Type type, final InstructionHandle start, final InstructionHandle end) {
        if ((index < 0) || (index > Const.MAX_SHORT)) {
            throw new ClassGenException("Invalid index index: " + index);
        }
        this.name = name;
        this.type = type;
        this.index = index;
        setStart(start);
        setEnd(end);
        this.origIndex = index;
        this.liveToEnd = end == null;
    }

    public LocalVariableGen(final int index, final String name, final Type type, final InstructionHandle start, final InstructionHandle end, final int origIndex) {
        this(index, name, type, start, end);
        this.origIndex = origIndex;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }

    @Override
    public boolean containsTarget(final InstructionHandle ih) {
        return (start == ih) || (end == ih);
    }

    public void dispose() {
        setStart(null);
        setEnd(null);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LocalVariableGen)) {
            return false;
        }
        final LocalVariableGen l = (LocalVariableGen) o;
        return (l.index == index) && (l.start == start) && (l.end == end);
    }

    public InstructionHandle getEnd() {
        return end;
    }

    public int getIndex() {
        return index;
    }

    public boolean getLiveToEnd() {
        return liveToEnd;
    }

    public LocalVariable getLocalVariable(final ConstantPoolGen cp) {
        int start_pc = 0;
        int length = 0;
        if ((start != null) && (end != null)) {
            start_pc = start.getPosition();
            length = end.getPosition() - start_pc;
            if ((end.getNext() == null) && liveToEnd) {
                length += end.getInstruction().getLength();
            }
        }
        final int name_index = cp.addUtf8(name);
        final int signature_index = cp.addUtf8(type.getSignature());
        return new LocalVariable(start_pc, length, name_index, signature_index, index, cp.getConstantPool(), origIndex);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public InstructionHandle getStart() {
        return start;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        // If the user changes the name or type, problems with the targeter hashmap will
        // occur.
        // Note: index cannot be part of hash as it may be changed by the user.
        return name.hashCode() ^ type.hashCode();
    }

    public void setEnd(final InstructionHandle end) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.end, end, this);
        this.end = end;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public void setLiveToEnd(final boolean live_to_end) {
        this.liveToEnd = live_to_end;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    public void setStart(final InstructionHandle start) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.start, start, this);
        this.start = start;
    }

    @Override
    public void setType(final Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LocalVariableGen(" + name + ", " + type + ", " + start + ", " + end + ")";
    }

    @Override
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        boolean targeted = false;
        if (start == old_ih) {
            targeted = true;
            setStart(new_ih);
        }
        if (end == old_ih) {
            targeted = true;
            setEnd(new_ih);
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih + ", but {" + start + ", " + end + "}");
        }
    }
}
