package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Constants;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class CodeException implements Constants {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public CodeException(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
    }

    public CodeException(int startPc, int endPc, int handlerPc, int catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CodeException other = (CodeException) obj;
        if (catchType != other.catchType)
            return false;
        if (endPc != other.endPc)
            return false;
        if (handlerPc != other.handlerPc)
            return false;
        if (startPc != other.startPc)
            return false;
        return true;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + catchType;
        result = prime * result + endPc;
        result = prime * result + handlerPc;
        result = prime * result + startPc;
        return result;
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
