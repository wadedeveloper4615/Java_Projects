package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<AnnotationEntryGen> annotationTable;

    public ParameterAnnotationEntryGen(ParameterAnnotationEntry parameterAnnotationEntry, ConstantPool constantPool) {
        this.annotationTable = new ArrayList<>();
        for (AnnotationEntry entry : parameterAnnotationEntry.getAnnotationTable()) {
            this.annotationTable.add(AnnotationEntryGen.read(entry, constantPool));
        }
    }
}