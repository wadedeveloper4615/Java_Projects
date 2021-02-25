package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class ExceptionTable extends Attribute {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExceptionTable other = (ExceptionTable) obj;
        if (!Arrays.equals(exceptionIndexTable, other.exceptionIndexTable))
            return false;
        return true;
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public int getNumberOfExceptions() {
        return exceptionIndexTable == null ? 0 : exceptionIndexTable.length;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(exceptionIndexTable);
        return result;
    }

    @Override
    public String toString() {
        return "ExceptionTable [exceptionIndexTable=" + Arrays.toString(exceptionIndexTable) + "]";
    }
}
