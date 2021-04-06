package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class ParameterAnnotationEntry {
    private List<AnnotationEntry> annotationTable;

    public ParameterAnnotationEntry(DataInput input, ConstantPool constantPool) throws IOException {
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new ArrayList<>();
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable.add(AnnotationEntry.read(input, constantPool, false));
        }
    }
}
