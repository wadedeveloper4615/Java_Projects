package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

public class LineNumber {
    private final short startPc;
    private final short lineNumber;

    public LineNumber(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public LineNumber(int startPc, int lineNumber) {
        this.startPc = (short) startPc;
        this.lineNumber = (short) lineNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LineNumber other = (LineNumber) obj;
        if (lineNumber != other.lineNumber)
            return false;
        if (startPc != other.startPc)
            return false;
        return true;
    }

    public int getLineNumber() {
        return 0xffff & lineNumber;
    }

    public int getStartPC() {
        return 0xffff & startPc;
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
        return "LineNumber [startPc=" + startPc + ", lineNumber=" + lineNumber + "]";
    }
}
