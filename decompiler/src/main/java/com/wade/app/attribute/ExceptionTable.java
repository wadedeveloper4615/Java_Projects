package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public final class ExceptionTable extends Attribute {
    private int[] exceptionIndexTable;

    public ExceptionTable(final ExceptionTable c) {
        this(c.getNameIndex(), c.getLength(), c.getExceptionIndexTable(), c.getConstantPool());
    }

    public ExceptionTable(final int nameIndex, final int length, final DataInputStream input, final ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (int[]) null, constantPool);
        final int number_of_exceptions = input.readUnsignedShort();
        exceptionIndexTable = new int[number_of_exceptions];
        for (int i = 0; i < number_of_exceptions; i++) {
            exceptionIndexTable[i] = input.readUnsignedShort();
        }
    }

    public ExceptionTable(final int name_index, final int length, final int[] exceptionIndexTable, final ConstantPool constant_pool) {
        super(Const.ATTR_EXCEPTIONS, name_index, length, constant_pool);
        this.exceptionIndexTable = exceptionIndexTable != null ? exceptionIndexTable : new int[0];
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public String[] getExceptionNames() throws ClassFormatException {
        final String[] names = new String[exceptionIndexTable.length];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            names[i] = super.getConstantPool().getConstantString(exceptionIndexTable[i], Const.CONSTANT_Class).replace('/', '.');
        }
        return names;
    }

    public int getNumberOfExceptions() {
        return exceptionIndexTable == null ? 0 : exceptionIndexTable.length;
    }

    public void setExceptionIndexTable(final int[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable != null ? exceptionIndexTable : new int[0];
    }
}
