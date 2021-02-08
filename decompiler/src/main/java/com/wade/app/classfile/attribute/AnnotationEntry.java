package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.app.constantpool.ConstantPool;

public class AnnotationEntry {
    private int typeIndex;
    private ConstantPool constantPool;
    private boolean isRuntimeVisible;
    private List<ElementValuePair> elementValuePairs;

    public AnnotationEntry(int type_index, ConstantPool constant_pool, boolean isRuntimeVisible) {
        this.typeIndex = type_index;
        this.constantPool = constant_pool;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public List<ElementValuePair> getElementValuePairs() {
        return elementValuePairs;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    public static AnnotationEntry read(DataInputStream input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
        AnnotationEntry annotationEntry = new AnnotationEntry(input.readUnsignedShort(), constant_pool, isRuntimeVisible);
        int num_element_value_pairs = input.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList<>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            annotationEntry.elementValuePairs.add(new ElementValuePair(input.readUnsignedShort(), ElementValue.readElementValue(input, constant_pool), constant_pool));
        }
        return annotationEntry;
    }
}
