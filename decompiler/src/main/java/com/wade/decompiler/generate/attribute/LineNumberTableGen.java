package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.LineNumber;
import com.wade.decompiler.classfile.attribute.LineNumberTable;
import com.wade.decompiler.classfile.constant.ConstantPool;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        LineNumberTableGen other = (LineNumberTableGen) obj;
        if (!Arrays.equals(lineNumberTable, other.lineNumberTable)) return false;
        return true;
    }

    public LineNumberGen[] getLineNumberTable() {
        return lineNumberTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(lineNumberTable);
        return result;
    }

    @Override
    public String toString() {
        return "LineNumberTableGen [lineNumberTable=" + Arrays.toString(lineNumberTable) + "]";
    }
}
