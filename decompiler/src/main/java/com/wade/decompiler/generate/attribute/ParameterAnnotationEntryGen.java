package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class ParameterAnnotationEntryGen {
    private AnnotationEntryGen[] annotationTable;

    public ParameterAnnotationEntryGen(ParameterAnnotationEntry parameterAnnotationEntry, ConstantPool constantPool) {
        AnnotationEntry[] ae = parameterAnnotationEntry.getAnnotationEntries();
        int annotation_table_length = ae.length;
        this.annotationTable = new AnnotationEntryGen[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            this.annotationTable[i] = AnnotationEntryGen.read(ae[i], constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParameterAnnotationEntryGen other = (ParameterAnnotationEntryGen) obj;
        if (!Arrays.equals(annotationTable, other.annotationTable))
            return false;
        return true;
    }

    public AnnotationEntryGen[] getAnnotationTable() {
        return annotationTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(annotationTable);
        return result;
    }

    @Override
    public String toString() {
        return "ParameterAnnotationEntryGen [annotationTable=" + Arrays.toString(annotationTable) + "]";
    }

    public static ParameterAnnotationEntryGen[] createParameterAnnotationEntries(AttributeGen[] attrs) {
        List<ParameterAnnotationEntryGen> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (AttributeGen attribute : attrs) {
            if (attribute instanceof ParameterAnnotationsGen) {
                ParameterAnnotationsGen runtimeAnnotations = (ParameterAnnotationsGen) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationEntries());
            }
        }
        return accumulatedAnnotations.toArray(new ParameterAnnotationEntryGen[accumulatedAnnotations.size()]);
    }
}
