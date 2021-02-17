package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.Node;
import com.wade.decompiler.classfile.gen.Visitor;

public class LineNumber implements Cloneable, Node {
    private short startPc;
    private short lineNumber;

    LineNumber(DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }

    public LineNumber(int startPc, int lineNumber) {
        this.startPc = (short) startPc;
        this.lineNumber = (short) lineNumber;
    }

    public LineNumber(LineNumber c) {
        this(c.getStartPC(), c.getLineNumber());
    }

    @Override
    public void accept(Visitor v) {
        v.visitLineNumber(this);
    }

    public LineNumber copy() {
        try {
            return (LineNumber) clone();
        } catch (CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(startPc);
        file.writeShort(lineNumber);
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
