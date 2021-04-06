package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.attribute.LocalVariableTypeTable;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
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
}
