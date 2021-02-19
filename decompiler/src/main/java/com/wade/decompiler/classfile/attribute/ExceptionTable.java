package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ExceptionTable extends Attribute {
    private int[] exceptionIndexTable;
    private String[] exceptionTableNames;

    public ExceptionTable(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (int[]) null, constantPool);
        int number_of_exceptions = input.readUnsignedShort();
        exceptionIndexTable = new int[number_of_exceptions];
        for (int i = 0; i < number_of_exceptions; i++) {
            exceptionIndexTable[i] = input.readUnsignedShort();
        }
        this.exceptionTableNames = new String[exceptionIndexTable.length];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            this.exceptionTableNames[i] = constantPool.getConstantString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
    }

    public ExceptionTable(int name_index, int length, int[] exceptionIndexTable, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_EXCEPTIONS, name_index, length, constant_pool);
        this.exceptionIndexTable = exceptionIndexTable != null ? exceptionIndexTable : new int[0];
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public String[] getExceptionNames() {
        return exceptionTableNames;
    }

    public int getNumberOfExceptions() {
        return exceptionIndexTable == null ? 0 : exceptionIndexTable.length;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Exceptions: ");
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            buf.append(Utility.compactClassName(exceptionTableNames[i], false));
            if (i < exceptionIndexTable.length - 1) {
                buf.append(", ");
            }
        }
        return buf.toString();
    }
}
