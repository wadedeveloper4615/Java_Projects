package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.attribute.LocalVariableTable;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class LocalVariableTableGen extends AttributeGen {
    private LocalVariableGen[] localVariableTable;

    public LocalVariableTableGen(LocalVariableTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        LocalVariable[] localVariableTable = attribute.getLocalVariableTable();
        this.localVariableTable = new LocalVariableGen[localVariableTable.length];
        for (int i = 0; i < localVariableTable.length; i++) {
            this.localVariableTable[i] = new LocalVariableGen(localVariableTable[i], constantPool);
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
        LocalVariableTableGen other = (LocalVariableTableGen) obj;
        if (!Arrays.equals(localVariableTable, other.localVariableTable))
            return false;
        return true;
    }

    public LocalVariableGen[] getLocalVariableTable() {
        return localVariableTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(localVariableTable);
        return result;
    }

    @Override
    public String toString() {
        return "LocalVariableTableGen [localVariableTable=" + Arrays.toString(localVariableTable) + "]";
    }
}
