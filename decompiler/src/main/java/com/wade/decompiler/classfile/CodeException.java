package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Constants;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class CodeException implements Constants {
    private int startPc; // Range in the code the exception handler is
    private int endPc; // active. startPc is inclusive, endPc exclusive
    private int handlerPc;
    private int catchType;

    public CodeException(CodeException c) {
        this(c.getStartPC(), c.getEndPC(), c.getHandlerPC(), c.getCatchType());
    }

    public CodeException(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public CodeException(int startPc, int endPc, int handlerPc, int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
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

    public void setCatchType(int catchType) {
        this.catchType = catchType;
    }

    public void setEndPC(int endPc) {
        this.endPc = endPc;
    }

    public void setHandlerPC(int handlerPc) { // TODO unused
        this.handlerPc = handlerPc;
    }

    public void setStartPC(int startPc) { // TODO unused
        this.startPc = startPc;
    }

    @Override
    public String toString() {
        return "CodeException(startPc = " + startPc + ", endPc = " + endPc + ", handlerPc = " + handlerPc + ", catchType = " + catchType + ")";
    }

    public String toString(ConstantPool cp) {
        return toString(cp, true);
    }

    public String toString(ConstantPool cp, boolean verbose) {
        String str;
        if (catchType == 0) {
            str = "<Any exception>(0)";
        } else {
            str = Utility.compactClassName(cp.getConstantString(catchType, ClassFileConstants.CONSTANT_Class), false) + (verbose ? "(" + catchType + ")" : "");
        }
        return startPc + "\t" + endPc + "\t" + handlerPc + "\t" + str;
    }
}
