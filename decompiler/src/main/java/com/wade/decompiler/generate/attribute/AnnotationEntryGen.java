package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class AnnotationEntryGen {
    private List<ElementValuePairGen> elementValuePairs;
    private int typeIndex;
    private boolean isRuntimeVisible;

    public AnnotationEntryGen(AnnotationEntry annotationEntry) {
        this.typeIndex = annotationEntry.getTypeIndex();
        this.isRuntimeVisible = annotationEntry.isRuntimeVisible();
    }

    public static AnnotationEntryGen read(AnnotationEntry ae, ConstantPool constantPool) {
        AnnotationEntryGen annotationEntry = new AnnotationEntryGen(ae);
        if (annotationEntry.elementValuePairs == null) {
            annotationEntry.elementValuePairs = new ArrayList<>();
        }
        int num_element_value_pairs = annotationEntry.elementValuePairs.size();
        annotationEntry.elementValuePairs = new ArrayList<ElementValuePairGen>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            annotationEntry.elementValuePairs.add(new ElementValuePairGen(ae.getElementValuePairs().get(i), ElementValueGen.readElementValue(ae.getElementValuePairs().get(i).getElementValue(), constantPool), constantPool));
        }
        return annotationEntry;
    }
}
