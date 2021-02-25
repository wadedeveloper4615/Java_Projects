package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class LocalVariableTypeTable extends Attribute {
    private LocalVariable[] localVariableTypeTable;

    public LocalVariableTypeTable(int nameIdx, int len, DataInputStream input, ConstantPool cpool) throws IOException {
        this(nameIdx, len, (LocalVariable[]) null, cpool);
        int local_variable_type_table_length = input.readUnsignedShort();
        localVariableTypeTable = new LocalVariable[local_variable_type_table_length];
        for (int i = 0; i < local_variable_type_table_length; i++) {
            localVariableTypeTable[i] = new LocalVariable(input, cpool);
        }
    }

    public LocalVariableTypeTable(int name_index, int length, LocalVariable[] local_variable_table, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_LOCAL_VARIABLE_TYPE_TABLE, name_index, length, constant_pool);
        this.localVariableTypeTable = local_variable_table;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalVariableTypeTable other = (LocalVariableTypeTable) obj;
        if (!Arrays.equals(localVariableTypeTable, other.localVariableTypeTable))
            return false;
        return true;
    }

    public LocalVariable getLocalVariable(int index) {
        for (LocalVariable variable : localVariableTypeTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
    }

    public LocalVariable[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public int getTableLength() {
        return localVariableTypeTable == null ? 0 : localVariableTypeTable.length;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(localVariableTypeTable);
        return result;
    }

    @Override
    public String toString() {
        return "LocalVariableTypeTable [localVariableTypeTable=" + Arrays.toString(localVariableTypeTable) + "]";
    }
}
