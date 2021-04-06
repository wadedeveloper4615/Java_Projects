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
public class LocalVariableTable extends Attribute {
    private List<LocalVariable> localVariableTable;

    public LocalVariableTable(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (List<LocalVariable>) null, constantPool);
        int local_variable_table_length = input.readUnsignedShort();
        localVariableTable = new ArrayList<>();
        for (int i = 0; i < local_variable_table_length; i++) {
            localVariableTable.add(new LocalVariable(input, constantPool));
        }
    }

    public LocalVariableTable(int nameIndex, int length, List<LocalVariable> localVariableTable, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_LOCAL_VARIABLE_TABLE, nameIndex, length, constantPool);
        this.localVariableTable = localVariableTable;
    }

    public LocalVariable getLocalVariable(int index, int pc) {
        for (LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                int start_pc = variable.getStartPc();
                int end_pc = start_pc + variable.getLength();
                if ((pc >= start_pc) && (pc <= end_pc)) {
                    return variable;
                }
            }
        }
        return null;
    }
}
