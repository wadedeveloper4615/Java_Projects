package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LineNumber;

public class LineNumberGen {
    private int startPc;
    private int lineNumber;

    public LineNumberGen(LineNumber attribute) {
        this.startPc = attribute.getStartPC();
        this.lineNumber = attribute.getLineNumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LineNumberGen other = (LineNumberGen) obj;
        if (lineNumber != other.lineNumber)
            return false;
        if (startPc != other.startPc)
            return false;
        return true;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getStartPc() {
        return startPc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + lineNumber;
        result = prime * result + startPc;
        return result;
    }

    @Override
    public String toString() {
        return "LineNumberGen [startPc=" + startPc + ", lineNumber=" + lineNumber + "]";
    }
}
