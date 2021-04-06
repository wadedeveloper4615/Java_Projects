package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.attribute.ParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ParameterAnnotationsGen extends AttributeGen {
    private ParameterAnnotationEntryGen[] parameterAnnotationTable;

    public ParameterAnnotationsGen(ParameterAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        ParameterAnnotationEntry[] parameterAnnotationTable = attribute.getParameterAnnotationTable();
        int num_parameters = parameterAnnotationTable.length;
        this.parameterAnnotationTable = new ParameterAnnotationEntryGen[num_parameters];
        for (int i = 0; i < num_parameters; i++) {
            this.parameterAnnotationTable[i] = new ParameterAnnotationEntryGen(parameterAnnotationTable[i], constantPool);
        }
    }
}
