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

    public int getLineNumber() {
        return 0xffff & lineNumber;
    }

    public int getStartPC() {
        return 0xffff & startPc;
    }

    @Override
    public String toString() {
        return "LineNumber [startPc=" + startPc + ", lineNumber=" + lineNumber + "]";
    }
}
