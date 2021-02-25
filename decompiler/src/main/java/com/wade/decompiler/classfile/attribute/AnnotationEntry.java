package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.classfile.element.ElementValuePair;

public class AnnotationEntry {
    private int typeIndex;
    private ConstantPool constantPool;
    private boolean isRuntimeVisible;
    private List<ElementValuePair> elementValuePairs = new ArrayList<>();

    public AnnotationEntry(int type_index, ConstantPool constant_pool, boolean isRuntimeVisible) {
        this.typeIndex = type_index;
        this.constantPool = constant_pool;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public void addElementNameValuePair(ElementValuePair e) {
        elementValuePairs.add(e);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnnotationEntry other = (AnnotationEntry) obj;
        if (constantPool == null) {
            if (other.constantPool != null)
                return false;
        } else if (!constantPool.equals(other.constantPool))
            return false;
        if (elementValuePairs == null) {
            if (other.elementValuePairs != null)
                return false;
        } else if (!elementValuePairs.equals(other.elementValuePairs))
            return false;
        if (isRuntimeVisible != other.isRuntimeVisible)
            return false;
        if (typeIndex != other.typeIndex)
            return false;
        return true;
    }

    public int getAnnotationTypeIndex() {
        return typeIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ElementValuePair[] getElementValuePairs() {
        return elementValuePairs.toArray(new ElementValuePair[elementValuePairs.size()]);
    }

    public int getNumElementValuePairs() {
        return elementValuePairs.size();
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((constantPool == null) ? 0 : constantPool.hashCode());
        result = prime * result + ((elementValuePairs == null) ? 0 : elementValuePairs.hashCode());
        result = prime * result + (isRuntimeVisible ? 1231 : 1237);
        result = prime * result + typeIndex;
        return result;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setElementValuePairs(List<ElementValuePair> elementValuePairs) {
        this.elementValuePairs = elementValuePairs;
    }

    public void setRuntimeVisible(boolean isRuntimeVisible) {
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    @Override
    public String toString() {
        return "AnnotationEntry [typeIndex=" + typeIndex + ", constantPool=" + constantPool + ", isRuntimeVisible=" + isRuntimeVisible + ", elementValuePairs=" + elementValuePairs + "]";
    }

    public static AnnotationEntry read(DataInput input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
        int index = input.readUnsignedShort();
        AnnotationEntry annotationEntry = new AnnotationEntry(index, constant_pool, isRuntimeVisible);
        int num_element_value_pairs = input.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList<>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            index = input.readUnsignedShort();
            ElementValue element = ElementValue.readElementValue(input, constant_pool);
            annotationEntry.elementValuePairs.add(new ElementValuePair(index, element, constant_pool));
        }
        return annotationEntry;
    }
}
