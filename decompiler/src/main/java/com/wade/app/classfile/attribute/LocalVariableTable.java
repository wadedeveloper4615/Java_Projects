package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class LocalVariableTable extends Attribute {
    private LocalVariable[] localVariableTable;

    public LocalVariableTable(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (LocalVariable[]) null, constant_pool);
        int local_variable_table_length = input.readUnsignedShort();
        localVariableTable = new LocalVariable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            localVariableTable[i] = new LocalVariable(input, constant_pool);
        }
    }

    public LocalVariableTable(int nameIndex, int length, LocalVariable[] localVariableTable, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_LOCAL_VARIABLE_TABLE, nameIndex, length, constantPool);
        this.localVariableTable = localVariableTable;
    }

    public LocalVariable getLocalVariable(int index, int pc) {
        for (final LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                final int start_pc = variable.getStartPc();
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

    @Override
    public String toString() {
        return "LocalVariableTable=" + Arrays.toString(localVariableTable);
    }
}
