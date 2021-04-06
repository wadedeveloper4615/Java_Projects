package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LineNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class LineNumberGen {
    private int startPc;
    private int lineNumber;

    public LineNumberGen(LineNumber attribute) {
        this.startPc = attribute.getStartPc();
        this.lineNumber = attribute.getLineNumber();
    }
}
