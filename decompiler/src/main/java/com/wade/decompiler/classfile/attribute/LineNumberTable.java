package com.wade.decompiler.classfile.attribute;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
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
public class LineNumberTable extends Attribute {
    private LineNumber[] lineNumberTable;

    public LineNumberTable(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
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
}
