package com.wade.decompiler.generic.gen;

import java.util.Objects;

import com.wade.decompiler.classfile.attribute.LineNumber;
import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.InstructionTargeter;

public class LineNumberGen implements InstructionTargeter, Cloneable {
    private InstructionHandle ih;
    private int srcLine;

    public LineNumberGen(InstructionHandle ih, int src_line) {
        setInstruction(ih);
        setSourceLine(src_line);
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
        return this.ih == ih;
    }

    public InstructionHandle getInstruction() {
        return ih;
    }

    public LineNumber getLineNumber() {
        return new LineNumber(ih.getPosition(), srcLine);
    }

    public int getSourceLine() {
        return srcLine;
    }

    public void setInstruction(InstructionHandle instructionHandle) { // TODO could be package-protected?
        Objects.requireNonNull(instructionHandle, "instructionHandle");
        BranchInstruction.notifyTarget(this.ih, instructionHandle, this);
        this.ih = instructionHandle;
    }

    public void setSourceLine(int src_line) { // TODO could be package-protected?
        this.srcLine = src_line;
    }

    @Override
    public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
        if (old_ih != ih) {
            throw new ClassGenException("Not targeting " + old_ih + ", but " + ih + "}");
        }
        setInstruction(new_ih);
    }
}
