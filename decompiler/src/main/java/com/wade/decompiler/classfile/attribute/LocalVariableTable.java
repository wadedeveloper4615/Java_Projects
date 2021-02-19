package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class LocalVariableTable extends Attribute {
    private LocalVariable[] localVariableTable;

    public LocalVariableTable(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
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
        for (LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                int start_pc = variable.getStartPC();
                int end_pc = start_pc + variable.getLength();
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

    public void setLocalVariableTable(LocalVariable[] local_variable_table) {
        this.localVariableTable = local_variable_table;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < localVariableTable.length; i++) {
            buf.append(localVariableTable[i]);
            if (i < localVariableTable.length - 1) {
                buf.append('\n');
            }
        }
        return buf.toString();
    }
}
