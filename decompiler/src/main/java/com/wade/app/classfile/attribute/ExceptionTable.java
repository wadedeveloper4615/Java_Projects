package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public  class ExceptionTable extends Attribute {
    private int[] exceptionIndexTable;

    public ExceptionTable(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (int[]) null, constantPool);
         int number_of_exceptions = input.readUnsignedShort();
        exceptionIndexTable = new int[number_of_exceptions];
        for (int i = 0; i < number_of_exceptions; i++) {
            exceptionIndexTable[i] = input.readUnsignedShort();
        }
    }

    public ExceptionTable(int name_index, int length, int[] exceptionIndexTable, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_EXCEPTIONS, name_index, length, constant_pool);
        this.exceptionIndexTable = exceptionIndexTable != null ? exceptionIndexTable : new int[0];
    }

}
