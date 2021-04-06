package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<LocalVariableGen> localVariableTable;

    public LocalVariableTableGen(LocalVariableTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        List<LocalVariable> localVariableTable = attribute.getLocalVariableTable();
        this.localVariableTable = new ArrayList<>();
        for (LocalVariable entry:localVariableTable) {
            this.localVariableTable.add(new LocalVariableGen(entry, constantPool));
        }
    }

    public LocalVariableGen getLocalVariable(int index, int pc) {
        for (LocalVariableGen variable : localVariableTable) {
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
