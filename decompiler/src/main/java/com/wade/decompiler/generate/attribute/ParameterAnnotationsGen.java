package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.attribute.ParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ParameterAnnotationsGen extends AttributeGen {
    private List<ParameterAnnotationEntryGen> parameterAnnotationTable;

    public ParameterAnnotationsGen(ParameterAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        parameterAnnotationTable = new ArrayList<>();
        for (ParameterAnnotationEntry entry : attribute.getParameterAnnotationTable()) {
            parameterAnnotationTable.add(new ParameterAnnotationEntryGen(entry, constantPool));
        }
    }
}
