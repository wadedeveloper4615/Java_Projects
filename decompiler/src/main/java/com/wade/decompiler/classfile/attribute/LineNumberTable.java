package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.util.Utility;

public class LineNumberTable extends Attribute {
    private static int MAX_LINE_LENGTH = 72;
    private LineNumber[] lineNumberTable;

    public LineNumberTable(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (LineNumber[]) null, constant_pool);
        int line_number_table_length = input.readUnsignedShort();
        lineNumberTable = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            lineNumberTable[i] = new LineNumber(input);
        }
    }

    public LineNumberTable(int name_index, int length, LineNumber[] line_number_table, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_LINE_NUMBER_TABLE, name_index, length, constant_pool);
        this.lineNumberTable = line_number_table;
    }

    public LineNumber[] getLineNumberTable() {
        return lineNumberTable;
    }

    public int getSourceLine(int pos) {
        int l = 0;
        int r = lineNumberTable.length - 1;
        if (r < 0) {
            return -1;
        }
        int min_index = -1;
        int min = -1;
        do {
            int i = (l + r) >>> 1;
            int j = lineNumberTable[i].getStartPC();
            if (j == pos) {
                return lineNumberTable[i].getLineNumber();
            } else if (pos < j) {
                r = i - 1;
            } else {
                l = i + 1;
            }
            if (j < pos && j > min) {
                min = j;
                min_index = i;
            }
        } while (l <= r);
        if (min_index < 0) {
            return -1;
        }
        return lineNumberTable[min_index].getLineNumber();
    }

    public int getTableLength() {
        return lineNumberTable == null ? 0 : lineNumberTable.length;
    }

    public void setLineNumberTable(LineNumber[] lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    @Override
    public String toString() {
        return "LineNumberTable [lineNumberTable=" + Utility.toString(lineNumberTable) + "]";
    }
}
