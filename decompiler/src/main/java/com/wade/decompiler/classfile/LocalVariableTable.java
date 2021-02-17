package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class LocalVariableTable extends Attribute {
    private LocalVariable[] localVariableTable;

    LocalVariableTable(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
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

    public LocalVariableTable(LocalVariableTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTable(), c.getConstantPool());
    }

    @Override
    public void accept(Visitor v) {
        v.visitLocalVariableTable(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        LocalVariableTable c = (LocalVariableTable) clone();
        c.localVariableTable = new LocalVariable[localVariableTable.length];
        for (int i = 0; i < localVariableTable.length; i++) {
            c.localVariableTable[i] = localVariableTable[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(localVariableTable.length);
        for (LocalVariable variable : localVariableTable) {
            variable.dump(file);
        }
    }

    @java.lang.Deprecated
    public LocalVariable getLocalVariable(int index) {
        for (LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
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
