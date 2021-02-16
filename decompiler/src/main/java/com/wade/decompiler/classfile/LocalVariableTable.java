package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public class LocalVariableTable extends Attribute {
    private LocalVariable[] localVariableTable; // variables

    LocalVariableTable(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (LocalVariable[]) null, constant_pool);
        final int local_variable_table_length = input.readUnsignedShort();
        localVariableTable = new LocalVariable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            localVariableTable[i] = new LocalVariable(input, constant_pool);
        }
    }

    public LocalVariableTable(final int nameIndex, final int length, final LocalVariable[] localVariableTable, final ConstantPool constantPool) {
        super(Const.ATTR_LOCAL_VARIABLE_TABLE, nameIndex, length, constantPool);
        this.localVariableTable = localVariableTable;
    }

    public LocalVariableTable(final LocalVariableTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTable(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLocalVariableTable(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final LocalVariableTable c = (LocalVariableTable) clone();
        c.localVariableTable = new LocalVariable[localVariableTable.length];
        for (int i = 0; i < localVariableTable.length; i++) {
            c.localVariableTable[i] = localVariableTable[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(localVariableTable.length);
        for (final LocalVariable variable : localVariableTable) {
            variable.dump(file);
        }
    }

    @java.lang.Deprecated
    public final LocalVariable getLocalVariable(final int index) {
        for (final LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
    }

    public final LocalVariable getLocalVariable(final int index, final int pc) {
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

    public final LocalVariable[] getLocalVariableTable() {
        return localVariableTable;
    }

    public final int getTableLength() {
        return localVariableTable == null ? 0 : localVariableTable.length;
    }

    public final void setLocalVariableTable(final LocalVariable[] local_variable_table) {
        this.localVariableTable = local_variable_table;
    }

    @Override
    public final String toString() {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < localVariableTable.length; i++) {
            buf.append(localVariableTable[i]);
            if (i < localVariableTable.length - 1) {
                buf.append('\n');
            }
        }
        return buf.toString();
    }
}
