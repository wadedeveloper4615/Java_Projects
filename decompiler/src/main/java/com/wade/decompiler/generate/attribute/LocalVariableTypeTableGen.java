package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<LocalVariableGen> localVariableTypeTable;

    public LocalVariableTypeTableGen(LocalVariableTypeTable attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        localVariableTypeTable = new ArrayList<>();
        for (LocalVariable entry : attribute.getLocalVariableTypeTable()) {
            localVariableTypeTable.add(new LocalVariableGen(entry, constantPool));
        }
    }
}
