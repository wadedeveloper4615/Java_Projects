package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;

public class LocalVariableTypeTable extends Attribute {
    private LocalVariable[] localVariableTypeTable;

    public LocalVariableTypeTable(final int nameIdx, final int len, final DataInput input, final ConstantPool cpool) throws IOException {
        this(nameIdx, len, (LocalVariable[]) null, cpool);
        final int local_variable_type_table_length = input.readUnsignedShort();
        localVariableTypeTable = new LocalVariable[local_variable_type_table_length];
        for (int i = 0; i < local_variable_type_table_length; i++) {
            localVariableTypeTable[i] = new LocalVariable(input, cpool);
        }
    }

    public LocalVariableTypeTable(final int name_index, final int length, final LocalVariable[] local_variable_table, final ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_LOCAL_VARIABLE_TYPE_TABLE, name_index, length, constant_pool);
        this.localVariableTypeTable = local_variable_table;
    }

    public LocalVariableTypeTable(final LocalVariableTypeTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTypeTable(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLocalVariableTypeTable(this);
    }

    @Override
    public Attribute copy(final ConstantPool constant_pool) {
        final LocalVariableTypeTable c = (LocalVariableTypeTable) clone();
        c.localVariableTypeTable = new LocalVariable[localVariableTypeTable.length];
        for (int i = 0; i < localVariableTypeTable.length; i++) {
            c.localVariableTypeTable[i] = localVariableTypeTable[i].copy();
        }
        c.setConstantPool(constant_pool);
        return c;
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(localVariableTypeTable.length);
        for (final LocalVariable variable : localVariableTypeTable) {
            variable.dump(file);
        }
    }

    public final LocalVariable getLocalVariable(final int index) {
        for (final LocalVariable variable : localVariableTypeTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
    }

    public final LocalVariable[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public final int getTableLength() {
        return localVariableTypeTable == null ? 0 : localVariableTypeTable.length;
    }

    public final void setLocalVariableTable(final LocalVariable[] local_variable_table) {
        this.localVariableTypeTable = local_variable_table;
    }

    @Override
    public final String toString() {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < localVariableTypeTable.length; i++) {
            buf.append(localVariableTypeTable[i].toStringShared(true));
            if (i < localVariableTypeTable.length - 1) {
                buf.append('\n');
            }
        }
        return buf.toString();
    }
}
