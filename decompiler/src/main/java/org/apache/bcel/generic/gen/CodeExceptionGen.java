package org.apache.bcel.generic.gen;

import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.base.BranchInstruction;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionTargeter;

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

    public CodeException getCodeException(final ConstantPoolGen cp) {
        return new CodeException(startPc.getPosition(), endPc.getPosition() + endPc.getInstruction().getLength(), handlerPc.getPosition(), (catchType == null) ? 0 : cp.addClass(catchType));
    }

    public void setStartPC(final InstructionHandle start_pc) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.startPc, start_pc, this);
        this.startPc = start_pc;
    }

    public void setEndPC(final InstructionHandle end_pc) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.endPc, end_pc, this);
        this.endPc = end_pc;
    }

    public void setHandlerPC(final InstructionHandle handler_pc) { // TODO could be package-protected?
        BranchInstruction.notifyTarget(this.handlerPc, handler_pc, this);
        this.handlerPc = handler_pc;
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

    @Override
    public boolean containsTarget(final InstructionHandle ih) {
        return (startPc == ih) || (endPc == ih) || (handlerPc == ih);
    }

    public void setCatchType(final ObjectType catchType) {
        this.catchType = catchType;
    }

    public ObjectType getCatchType() {
        return catchType;
    }

    public InstructionHandle getStartPC() {
        return startPc;
    }

    public InstructionHandle getEndPC() {
        return endPc;
    }

    public InstructionHandle getHandlerPC() {
        return handlerPc;
    }

    @Override
    public String toString() {
        return "CodeExceptionGen(" + startPc + ", " + endPc + ", " + handlerPc + ")";
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }
}
