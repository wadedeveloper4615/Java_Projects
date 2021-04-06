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
public class ExceptionTable extends Attribute {
    private List<Integer> exceptionIndexTable;

    public ExceptionTable(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (List<Integer>) null, constantPool);
        int number_of_exceptions = input.readUnsignedShort();
        exceptionIndexTable = new ArrayList<>();
        for (int i = 0; i < number_of_exceptions; i++) {
            exceptionIndexTable.add(input.readUnsignedShort());
        }
    }

    public ExceptionTable(int nameIndex, int length, List<Integer> exceptionIndexTable, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_EXCEPTIONS, nameIndex, length, constantPool);
        this.exceptionIndexTable = exceptionIndexTable;
    }
}
