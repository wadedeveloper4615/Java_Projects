package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.classfile.element.ElementValuePair;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class AnnotationEntry {
    private int typeIndex;
    private ConstantPool constantPool;
    private boolean isRuntimeVisible;
    private List<ElementValuePair> elementValuePairs = new ArrayList<>();

    public AnnotationEntry(int type_index, ConstantPool constantPool, boolean isRuntimeVisible) {
        this.typeIndex = type_index;
        this.constantPool = constantPool;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public void addElementNameValuePair(ElementValuePair e) {
        elementValuePairs.add(e);
    }

    public static AnnotationEntry read(DataInput input, ConstantPool constantPool, boolean isRuntimeVisible) throws IOException {
        int index = input.readUnsignedShort();
        AnnotationEntry annotationEntry = new AnnotationEntry(index, constantPool, isRuntimeVisible);
        int num_element_value_pairs = input.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList<>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            index = input.readUnsignedShort();
            ElementValue element = ElementValue.readElementValue(input, constantPool);
            annotationEntry.elementValuePairs.add(new ElementValuePair(index, element));
        }
        return annotationEntry;
    }
}
