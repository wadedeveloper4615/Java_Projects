package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.util.Utility;

public class LineNumberTable extends Attribute {
    private LineNumber[] lineNumberTable;

    public LineNumberTable(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (LineNumber[]) null, constantPool);
        int line_number_table_length = input.readUnsignedShort();
        lineNumberTable = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            lineNumberTable[i] = new LineNumber(input);
        }
    }

    public LineNumberTable(int nameIndex, int length, LineNumber[] line_number_table, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_LINE_NUMBER_TABLE, nameIndex, length, constantPool);
        this.lineNumberTable = line_number_table;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LineNumberTable other = (LineNumberTable) obj;
        if (!Arrays.equals(lineNumberTable, other.lineNumberTable))
            return false;
        return true;
    }

    public LineNumber[] getLineNumberTable() {
        return lineNumberTable;
    }

    public int getTableLength() {
        return lineNumberTable == null ? 0 : lineNumberTable.length;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(lineNumberTable);
        return result;
    }

    @Override
    public String toString() {
        return "LineNumberTable [" + Utility.toString(lineNumberTable) + "]";
    }
}
