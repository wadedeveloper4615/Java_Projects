package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.attribute.ParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ParameterAnnotationsGen other = (ParameterAnnotationsGen) obj;
        if (!Arrays.equals(parameterAnnotationTable, other.parameterAnnotationTable)) return false;
        return true;
    }

    public ParameterAnnotationEntryGen[] getParameterAnnotationEntries() {
        return parameterAnnotationTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(parameterAnnotationTable);
        return result;
    }

    @Override
    public String toString() {
        return "ParameterAnnotationsGen [parameterAnnotationTable=" + Arrays.toString(parameterAnnotationTable) + "]";
    }
}
