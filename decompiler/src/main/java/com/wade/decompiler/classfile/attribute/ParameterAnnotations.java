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
public abstract class ParameterAnnotations extends Attribute {
    private List<ParameterAnnotationEntry> parameterAnnotationTable;

    public ParameterAnnotations(ClassFileAttributes parameter_annotation_type, int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(parameter_annotation_type, nameIndex, length, (List<ParameterAnnotationEntry>) null, constantPool);
        int num_parameters = input.readUnsignedByte();
        parameterAnnotationTable = new ArrayList<>();
        for (int i = 0; i < num_parameters; i++) {
            parameterAnnotationTable.add(new ParameterAnnotationEntry(input, constantPool));
        }
    }

    public ParameterAnnotations(ClassFileAttributes parameterAnnotationType, int nameIndex, int length, List<ParameterAnnotationEntry> parameterAnnotationTable, ConstantPool constantPool) {
        super(parameterAnnotationType, nameIndex, length, constantPool);
        this.parameterAnnotationTable = parameterAnnotationTable;
    }
}
