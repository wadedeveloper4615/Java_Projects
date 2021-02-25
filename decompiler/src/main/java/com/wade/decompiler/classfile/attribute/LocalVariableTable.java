package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.util.Utility;

public class LocalVariableTable extends Attribute {
    private LocalVariable[] localVariableTable;

    public LocalVariableTable(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (LocalVariable[]) null, constantPool);
        int local_variable_table_length = input.readUnsignedShort();
        localVariableTable = new LocalVariable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            localVariableTable[i] = new LocalVariable(input, constantPool);
        }
    }

    public LocalVariableTable(int nameIndex, int length, LocalVariable[] localVariableTable, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_LOCAL_VARIABLE_TABLE, nameIndex, length, constantPool);
        this.localVariableTable = localVariableTable;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalVariableTable other = (LocalVariableTable) obj;
        if (!Arrays.equals(localVariableTable, other.localVariableTable))
            return false;
        return true;
    }

    public LocalVariable getLocalVariable(int index, int pc) {
        for (final LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                final int start_pc = variable.getStartPC();
                final int end_pc = start_pc + variable.getLength();
                if ((pc >= start_pc) && (pc <= end_pc)) {
                    return variable;
                }
            }
        }
        return null;
    }

    public LocalVariable[] getLocalVariableTable() {
        return localVariableTable;
    }

    public int getTableLength() {
        return localVariableTable == null ? 0 : localVariableTable.length;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(localVariableTable);
        return result;
    }

    public void setLocalVariableTable(LocalVariable[] local_variable_table) {
        this.localVariableTable = local_variable_table;
    }

    @Override
    public String toString() {
        return "LocalVariableTable [" + Utility.toString(localVariableTable) + "]";
    }
}
