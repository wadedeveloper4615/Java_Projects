package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ParameterAnnotationEntryGen {
    private AnnotationEntryGen[] annotationTable;

    public ParameterAnnotationEntryGen(ParameterAnnotationEntry parameterAnnotationEntry, ConstantPool constantPool) {
        AnnotationEntry[] ae = parameterAnnotationEntry.getAnnotationTable();
        int annotation_table_length = ae.length;
        this.annotationTable = new AnnotationEntryGen[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            this.annotationTable[i] = AnnotationEntryGen.read(ae[i], constantPool);
        }
    }

    public static ParameterAnnotationEntryGen[] createParameterAnnotationEntries(AttributeGen[] attrs) {
        List<ParameterAnnotationEntryGen> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (AttributeGen attribute : attrs) {
            if (attribute instanceof ParameterAnnotationsGen) {
                ParameterAnnotationsGen runtimeAnnotations = (ParameterAnnotationsGen) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationTable());
            }
        }
        return accumulatedAnnotations.toArray(new ParameterAnnotationEntryGen[accumulatedAnnotations.size()]);
    }
}
