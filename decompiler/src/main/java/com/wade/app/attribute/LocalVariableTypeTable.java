package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class LocalVariableTypeTable extends Attribute {
    private LocalVariable[] localVariableTypeTable;

    public LocalVariableTypeTable(final int nameIdx, final int len, final DataInputStream input, final ConstantPool cpool) throws IOException {
        this(nameIdx, len, (LocalVariable[]) null, cpool);

        final int local_variable_type_table_length = input.readUnsignedShort();
        localVariableTypeTable = new LocalVariable[local_variable_type_table_length];

        for (int i = 0; i < local_variable_type_table_length; i++) {
            localVariableTypeTable[i] = new LocalVariable(input, cpool);
        }
    }

    public LocalVariableTypeTable(final int name_index, final int length, final LocalVariable[] local_variable_table, final ConstantPool constant_pool) {
        super(Const.ATTR_LOCAL_VARIABLE_TYPE_TABLE, name_index, length, constant_pool);
        this.localVariableTypeTable = local_variable_table;
    }

    public LocalVariableTypeTable(final LocalVariableTypeTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTypeTable(), c.getConstantPool());
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
}
