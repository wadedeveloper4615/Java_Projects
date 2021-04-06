package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.attribute.LocalVariableTable;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
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

    public LocalVariableGen getLocalVariable(final int index, final int pc) {
        for (final LocalVariableGen variable : localVariableTable) {
            if (variable.getIndex() == index) {
                final int start_pc = variable.getStartPc();
                final int end_pc = start_pc + variable.getLength();
                if ((pc >= start_pc) && (pc <= end_pc)) {
                    return variable;
                }
            }
        }
        return null;
    }
}
