package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.Annotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class AnnotationsGen extends AttributeGen {
    private boolean isRuntimeVisible;
    private AnnotationEntryGen[] annotationTable;

    public AnnotationsGen(Annotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.isRuntimeVisible = attribute.isRuntimeVisible();
        AnnotationEntry[] annotationEntries = attribute.getAnnotationTable();
        this.annotationTable = new AnnotationEntryGen[annotationEntries.length];
        for (int i = 0; i < annotationEntries.length; i++) {
            this.annotationTable[i] = AnnotationEntryGen.read(annotationEntries[i], constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AnnotationsGen other = (AnnotationsGen) obj;
        if (!Arrays.equals(annotationTable, other.annotationTable)) return false;
        if (isRuntimeVisible != other.isRuntimeVisible) return false;
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
        result = prime * result + (isRuntimeVisible ? 1231 : 1237);
        return result;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    @Override
    public String toString() {
        return "AnnotationsGen [isRuntimeVisible=" + isRuntimeVisible + ", annotationTable=" + Arrays.toString(annotationTable) + "]";
    }
}
