package com.wade.decompiler.classfile.attribute;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.DataInput;
import java.io.IOException;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
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
}
