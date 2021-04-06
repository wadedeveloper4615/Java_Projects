package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LineNumberTable extends Attribute {
    private List<LineNumber> lineNumberTable;

    public LineNumberTable(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (List<LineNumber>) null, constantPool);
        int line_number_table_length = input.readUnsignedShort();
        lineNumberTable = new ArrayList<>();
        for (int i = 0; i < line_number_table_length; i++) {
            lineNumberTable.add(new LineNumber(input));
        }
    }

    public LineNumberTable(int nameIndex, int length, List<LineNumber> line_number_table, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_LINE_NUMBER_TABLE, nameIndex, length, constantPool);
        this.lineNumberTable = line_number_table;
    }
}
