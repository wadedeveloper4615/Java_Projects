package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

public final class LineNumber implements Cloneable, Node {
    private short startPc;
    private short lineNumber;

    public LineNumber(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public LineNumber(final int startPc, final int lineNumber) {
        this.startPc = (short) startPc;
        this.lineNumber = (short) lineNumber;
    }

    public LineNumber(final LineNumber c) {
        this(c.getStartPC(), c.getLineNumber());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLineNumber(this);
    }

    public LineNumber copy() {
        try {
            return (LineNumber) clone();
        } catch (final CloneNotSupportedException e) {
        }
        return null;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(startPc);
        file.writeShort(lineNumber);
    }

    public int getLineNumber() {
        return 0xffff & lineNumber;
    }

    public int getStartPC() {
        return 0xffff & startPc;
    }

    public void setLineNumber(final int lineNumber) {
        this.lineNumber = (short) lineNumber;
    }

    public void setStartPC(final int startPc) {
        this.startPc = (short) startPc;
    }

    @Override
    public String toString() {
        return "LineNumber(" + startPc + ", " + lineNumber + ")";
    }
}
