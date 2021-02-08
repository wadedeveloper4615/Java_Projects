package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public  class LineNumber {
    private short startPc;
    private short lineNumber;

    public LineNumber( DataInputStream file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public LineNumber(int startPc, int lineNumber) {
        this.startPc = (short) startPc;
        this.lineNumber = (short) lineNumber;
    }

    public short getLineNumber() {
        return lineNumber;
    }

    public short getStartPc() {
        return startPc;
    }
}
