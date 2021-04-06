package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LineNumber;
import com.wade.decompiler.classfile.attribute.LineNumberTable;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LineNumberTableGen extends AttributeGen {
    private LineNumberGen[] lineNumberTable;

    public LineNumberTableGen(LineNumberTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        LineNumber[] lineNumberTable = attribute.getLineNumberTable();
        this.lineNumberTable = new LineNumberGen[lineNumberTable.length];
        for (int i = 0; i < lineNumberTable.length; i++) {
            this.lineNumberTable[i] = new LineNumberGen(lineNumberTable[i]);
        }
    }
}
