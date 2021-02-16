package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Constants;
import com.wade.decompiler.enums.ClassFileConstants;

public final class CodeException implements Cloneable, Node, Constants {
    private int startPc; // Range in the code the exception handler is
    private int endPc; // active. startPc is inclusive, endPc exclusive
    private int handlerPc;
    private int catchType;

    public CodeException(final CodeException c) {
        this(c.getStartPC(), c.getEndPC(), c.getHandlerPC(), c.getCatchType());
    }

    CodeException(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public CodeException(final int startPc, final int endPc, final int handlerPc, final int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitCodeException(this);
    }

    public CodeException copy() {
        try {
            return (CodeException) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(startPc);
        file.writeShort(endPc);
        file.writeShort(handlerPc);
        file.writeShort(catchType);
    }

    public int getCatchType() {
        return catchType;
    }

    public int getEndPC() {
        return endPc;
    }

    public int getHandlerPC() {
        return handlerPc;
    }

    public int getStartPC() {
        return startPc;
    }

    public void setCatchType(final int catchType) {
        this.catchType = catchType;
    }

    public void setEndPC(final int endPc) {
        this.endPc = endPc;
    }

    public void setHandlerPC(final int handlerPc) { // TODO unused
        this.handlerPc = handlerPc;
    }

    public void setStartPC(final int startPc) { // TODO unused
        this.startPc = startPc;
    }

    @Override
    public String toString() {
        return "CodeException(startPc = " + startPc + ", endPc = " + endPc + ", handlerPc = " + handlerPc + ", catchType = " + catchType + ")";
    }

    public String toString(final ConstantPool cp) {
        return toString(cp, true);
    }

    public String toString(final ConstantPool cp, final boolean verbose) {
        String str;
        if (catchType == 0) {
            str = "<Any exception>(0)";
        } else {
            str = Utility.compactClassName(cp.getConstantString(catchType, ClassFileConstants.CONSTANT_Class), false) + (verbose ? "(" + catchType + ")" : "");
        }
        return startPc + "\t" + endPc + "\t" + handlerPc + "\t" + str;
    }
}
