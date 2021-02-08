package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class LineNumberTable extends Attribute {
    private static int MAX_LINE_LENGTH = 72;
    private LineNumber[] lineNumberTable;

    public LineNumberTable(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
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

    @Override
    public String toString() {
        return "LineNumberTable [lineNumberTable=" + Arrays.toString(lineNumberTable) + "]";
    }

}
