package com.wade.decompiler.generic.gen;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.InstructionTargeter;
import com.wade.decompiler.generic.base.NamedAndTyped;
import com.wade.decompiler.generic.type.Type;

public class LocalVariableGen implements InstructionTargeter, NamedAndTyped, Cloneable {
    private int index;
    private String name;
    private Type type;
    private InstructionHandle start;
    private InstructionHandle end;
    private int origIndex; // never changes; used to match up with LocalVariableTypeTable entries
    private boolean liveToEnd;

    public LocalVariableGen(int index, String name, Type type, InstructionHandle start, InstructionHandle end) {
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

    public LocalVariableGen(int index, String name, Type type, InstructionHandle start, InstructionHandle end, int origIndex) {
        this(index, name, type, start, end);
        this.origIndex = origIndex;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }

    @Override
    public boolean containsTarget(InstructionHandle ih) {
        return (start == ih) || (end == ih);
    }

    public void dispose() {
        setStart(null);
        setEnd(null);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LocalVariableGen)) {
            return false;
        }
        LocalVariableGen l = (LocalVariableGen) o;
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

    public LocalVariable getLocalVariable(ConstantPoolGen cp) {
        int start_pc = 0;
        int length = 0;
        if ((start != null) && (end != null)) {
            start_pc = start.getPosition();
            length = end.getPosition() - start_pc;
            if ((end.getNext() == null) && liveToEnd) {
                length += end.getInstruction().getLength();
            }
        }
        int name_index = cp.addUtf8(name);
        int signature_index = cp.addUtf8(type.getSignature());
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

    public void setEnd(InstructionHandle end) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.end, end, this);
        this.end = end;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLiveToEnd(boolean live_to_end) {
        this.liveToEnd = live_to_end;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setStart(InstructionHandle start) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.start, start, this);
        this.start = start;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LocalVariableGen(" + name + ", " + type + ", " + start + ", " + end + ")";
    }

    @Override
    public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
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
