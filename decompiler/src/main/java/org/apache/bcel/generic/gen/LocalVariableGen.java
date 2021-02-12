package org.apache.bcel.generic.gen;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.NamedAndTyped;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionTargeter;

public class LocalVariableGen implements InstructionTargeter, NamedAndTyped, Cloneable {
    private int index;
    private String name;
    private Type type;
    private InstructionHandle start;
    private InstructionHandle end;
    private int origIndex;
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

    public void setIndex(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public void setLiveToEnd(final boolean live_to_end) {
        this.liveToEnd = live_to_end;
    }

    public boolean getLiveToEnd() {
        return liveToEnd;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setType(final Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    public InstructionHandle getStart() {
        return start;
    }

    public InstructionHandle getEnd() {
        return end;
    }

    public void setStart(final InstructionHandle start) {
        BranchInstruction.notifyTarget(this.start, start, this);
        this.start = start;
    }

    public void setEnd(final InstructionHandle end) {
        BranchInstruction.notifyTarget(this.end, end, this);
        this.end = end;
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

    void dispose() {
        setStart(null);
        setEnd(null);
    }

    @Override
    public boolean containsTarget(final InstructionHandle ih) {
        return (start == ih) || (end == ih);
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ type.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LocalVariableGen)) {
            return false;
        }
        final LocalVariableGen l = (LocalVariableGen) o;
        return (l.index == index) && (l.start == start) && (l.end == end);
    }

    @Override
    public String toString() {
        return "LocalVariableGen(" + name + ", " + type + ", " + start + ", " + end + ")";
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported");
        }
    }
}
