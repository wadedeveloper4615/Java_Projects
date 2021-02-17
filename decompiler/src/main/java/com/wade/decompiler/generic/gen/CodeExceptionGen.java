package com.wade.decompiler.generic.gen;

import com.wade.decompiler.classfile.CodeException;
import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.InstructionTargeter;
import com.wade.decompiler.generic.base.ObjectType;

public final class CodeExceptionGen implements InstructionTargeter, Cloneable {
    private InstructionHandle startPc;
    private InstructionHandle endPc;
    private InstructionHandle handlerPc;
    private ObjectType catchType;

    public CodeExceptionGen(final InstructionHandle startPc, final InstructionHandle endPc, final InstructionHandle handlerPc, final ObjectType catchType) {
        setStartPC(startPc);
        setEndPC(endPc);
        setHandlerPC(handlerPc);
        this.catchType = catchType;
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
        return (startPc == ih) || (endPc == ih) || (handlerPc == ih);
    }

    public ObjectType getCatchType() {
        return catchType;
    }

    public CodeException getCodeException(final ConstantPoolGen cp) {
        return new CodeException(startPc.getPosition(), endPc.getPosition() + endPc.getInstruction().getLength(), handlerPc.getPosition(), (catchType == null) ? 0 : cp.addClass(catchType));
    }

    public InstructionHandle getEndPC() {
        return endPc;
    }

    public InstructionHandle getHandlerPC() {
        return handlerPc;
    }

    public InstructionHandle getStartPC() {
        return startPc;
    }

    public void setCatchType(final ObjectType catchType) {
        this.catchType = catchType;
    }

    public void setEndPC(final InstructionHandle end_pc) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.endPc, end_pc, this);
        this.endPc = end_pc;
    }

    public void setHandlerPC(final InstructionHandle handler_pc) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.handlerPc, handler_pc, this);
        this.handlerPc = handler_pc;
    }

    public void setStartPC(final InstructionHandle start_pc) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.startPc, start_pc, this);
        this.startPc = start_pc;
    }

    @Override
    public String toString() {
        return "CodeExceptionGen(" + startPc + ", " + endPc + ", " + handlerPc + ")";
    }

    @Override
    public void updateTarget(final InstructionHandle old_ih, final InstructionHandle new_ih) {
        boolean targeted = false;
        if (startPc == old_ih) {
            targeted = true;
            setStartPC(new_ih);
        }
        if (endPc == old_ih) {
            targeted = true;
            setEndPC(new_ih);
        }
        if (handlerPc == old_ih) {
            targeted = true;
            setHandlerPC(new_ih);
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih + ", but {" + startPc + ", " + endPc + ", " + handlerPc + "}");
        }
    }
}
