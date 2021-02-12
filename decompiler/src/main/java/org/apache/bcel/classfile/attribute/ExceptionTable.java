package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;
import org.apache.bcel.exceptions.ClassFormatException;

public final class ExceptionTable extends Attribute {
    private int[] exceptionIndexTable; // constant pool

    public ExceptionTable(final ExceptionTable c) {
        this(c.getNameIndex(), c.getLength(), c.getExceptionIndexTable(), c.getConstantPool());
    }

    public ExceptionTable(final int nameIndex, final int length, final DataInput input, final ConstantPool constantPool) throws IOException {
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

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionTable(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final ExceptionTable c = (ExceptionTable) clone();
        if (exceptionIndexTable != null) {
            c.exceptionIndexTable = new int[exceptionIndexTable.length];
            System.arraycopy(exceptionIndexTable, 0, c.exceptionIndexTable, 0, exceptionIndexTable.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(exceptionIndexTable.length);
        for (final int index : exceptionIndexTable) {
            file.writeShort(index);
        }
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public String[] getExceptionNames() {
        final String[] names = new String[exceptionIndexTable.length];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            names[i] = super.getConstantPool().getConstantString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
        return names;
    }

    public int getNumberOfExceptions() {
        return exceptionIndexTable == null ? 0 : exceptionIndexTable.length;
    }

    public void setExceptionIndexTable(final int[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable != null ? exceptionIndexTable : new int[0];
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        try {
            String str;
            buf.append("Exceptions: ");
            for (int i = 0; i < exceptionIndexTable.length; i++) {
                str = super.getConstantPool().getConstantString(exceptionIndexTable[i], ClassFileConstants.CONSTANT_Class);
                buf.append(Utility.compactClassName(str, false));
                if (i < exceptionIndexTable.length - 1) {
                    buf.append(", ");
                }
            }
        } catch (ClassFormatException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}