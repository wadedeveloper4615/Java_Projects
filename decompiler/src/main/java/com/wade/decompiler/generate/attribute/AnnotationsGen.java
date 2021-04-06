package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.Annotations;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class AnnotationsGen extends AttributeGen {
    private boolean isRuntimeVisible;
    private List<AnnotationEntryGen> annotationTable;

    public AnnotationsGen(Annotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.isRuntimeVisible = attribute.isRuntimeVisible();
        this.annotationTable = new ArrayList<>();
        for (AnnotationEntry entry : attribute.getAnnotationTable()) {
            this.annotationTable.add(AnnotationEntryGen.read(entry, constantPool));
        }
    }
}
