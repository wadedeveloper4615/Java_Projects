package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

public class LineNumber {
    private short startPc;
    private short lineNumber;

    public LineNumber(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public LineNumber(int startPc, int lineNumber) {
        this.startPc = (short) startPc;
        this.lineNumber = (short) lineNumber;
    }

    public LineNumber(LineNumber c) {
        this(c.getStartPC(), c.getLineNumber());
    }

    public int getLineNumber() {
        return 0xffff & lineNumber;
    }

    public int getStartPC() {
        return 0xffff & startPc;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = (short) lineNumber;
    }

    public void setStartPC(int startPc) {
        this.startPc = (short) startPc;
    }

    @Override
    public String toString() {
        return "LineNumber(" + startPc + ", " + lineNumber + ")";
    }
}
