package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.enums.ClassFileConstants;

public class ExceptionTable extends Attribute {
    private int[] exceptionIndexTable; // constant pool

    public ExceptionTable(ExceptionTable c) {
        this(c.getNameIndex(), c.getLength(), c.getExceptionIndexTable(), c.getConstantPool());
    }

    public ExceptionTable(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
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
    public void accept(Visitor v) {
        v.visitExceptionTable(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        ExceptionTable c = (ExceptionTable) clone();
        if (exceptionIndexTable != null) {
            c.exceptionIndexTable = new int[exceptionIndexTable.length];
            System.arraycopy(exceptionIndexTable, 0, c.exceptionIndexTable, 0, exceptionIndexTable.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(exceptionIndexTable.length);
        for (int index : exceptionIndexTable) {
            file.writeShort(index);
        }
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public String[] getExceptionNames() {
        String[] names = new String[exceptionIndexTable.length];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            names[i] = super.getConstantPool().getConstantString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
        return names;
    }

    public int getNumberOfExceptions() {
        return exceptionIndexTable == null ? 0 : exceptionIndexTable.length;
    }

    public void setExceptionIndexTable(int[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable != null ? exceptionIndexTable : new int[0];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        String str;
        buf.append("Exceptions: ");
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            str = super.getConstantPool().getConstantString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class);
            buf.append(Utility.compactClassName(str, false));
            if (i < exceptionIndexTable.length - 1) {
                buf.append(", ");
            }
        }
        return buf.toString();
    }
}
