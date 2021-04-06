package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class Annotations extends Attribute {
    private List<AnnotationEntry> annotationTable;
    private boolean isRuntimeVisible;

    public Annotations(ClassFileAttributes annotation_type, int nameIndex, int length, DataInput input, ConstantPool constantPool, boolean isRuntimeVisible) throws IOException {
        this(annotation_type, nameIndex, length, (List<AnnotationEntry>) null, constantPool, isRuntimeVisible);
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new ArrayList<>();
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable.add(AnnotationEntry.read(input, constantPool, isRuntimeVisible));
        }
    }

    public Annotations(ClassFileAttributes annotationType, int nameIndex, int length, List<AnnotationEntry> annotationTable, ConstantPool constantPool, boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

}
