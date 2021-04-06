package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LocalVariableTypeTable extends Attribute {
    private List<LocalVariable> localVariableTypeTable;

    public LocalVariableTypeTable(int nameIdx, int len, DataInput input, ConstantPool cpool) throws IOException {
        this(nameIdx, len, (List<LocalVariable>) null, cpool);
        int local_variable_type_table_length = input.readUnsignedShort();
        localVariableTypeTable = new ArrayList<>();
        for (int i = 0; i < local_variable_type_table_length; i++) {
            localVariableTypeTable.add(new LocalVariable(input, cpool));
        }
    }

    public LocalVariableTypeTable(int nameIndex, int length, List<LocalVariable> local_variable_table, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_LOCAL_VARIABLE_TYPE_TABLE, nameIndex, length, constantPool);
        this.localVariableTypeTable = local_variable_table;
    }

    public LocalVariable getLocalVariable(int index) {
        for (LocalVariable variable : localVariableTypeTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
    }
}
