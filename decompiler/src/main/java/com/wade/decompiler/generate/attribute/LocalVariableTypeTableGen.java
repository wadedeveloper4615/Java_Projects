package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.attribute.LocalVariableTypeTable;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class LocalVariableTypeTableGen extends AttributeGen {
    private LocalVariableGen[] localVariableTypeTable;

    public LocalVariableTypeTableGen(LocalVariableTypeTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        LocalVariable[] localVariableTypeTable = attribute.getLocalVariableTypeTable();
        int local_variable_type_table_length = localVariableTypeTable.length;
        this.localVariableTypeTable = new LocalVariableGen[local_variable_type_table_length];
        for (int i = 0; i < local_variable_type_table_length; i++) {
            this.localVariableTypeTable[i] = new LocalVariableGen(localVariableTypeTable[i], constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocalVariableTypeTableGen other = (LocalVariableTypeTableGen) obj;
        if (!Arrays.equals(localVariableTypeTable, other.localVariableTypeTable))
            return false;
        return true;
    }

    public LocalVariableGen getLocalVariable(int index) {
        for (LocalVariableGen variable : localVariableTypeTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
    }

    public LocalVariableGen[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(localVariableTypeTable);
        return result;
    }

    @Override
    public String toString() {
        return "LocalVariableTypeTableGen [localVariableTypeTable=" + Arrays.toString(localVariableTypeTable) + "]";
    }
}
