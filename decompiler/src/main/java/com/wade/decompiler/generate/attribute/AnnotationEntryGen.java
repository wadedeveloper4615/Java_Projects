package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AnnotationEntryGen other = (AnnotationEntryGen) obj;
        if (elementValuePairs == null) {
            if (other.elementValuePairs != null) return false;
        } else if (!elementValuePairs.equals(other.elementValuePairs)) return false;
        if (isRuntimeVisible != other.isRuntimeVisible) return false;
        if (typeIndex != other.typeIndex) return false;
        return true;
    }

    public List<ElementValuePairGen> getElementValuePairs() {
        return elementValuePairs;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elementValuePairs == null) ? 0 : elementValuePairs.hashCode());
        result = prime * result + (isRuntimeVisible ? 1231 : 1237);
        result = prime * result + typeIndex;
        return result;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    @Override
    public String toString() {
        return "AnnotationEntryGen [elementValuePairs=" + elementValuePairs + ", typeIndex=" + typeIndex + ", isRuntimeVisible=" + isRuntimeVisible + "]";
    }
}
