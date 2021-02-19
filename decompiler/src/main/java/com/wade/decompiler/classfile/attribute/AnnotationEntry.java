package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.classfile.element.ElementValuePair;
import com.wade.decompiler.enums.ClassFileConstants;

public class AnnotationEntry {
    private int typeIndex;
    private ConstantPool constantPool;
    private boolean isRuntimeVisible;
    private List<ElementValuePair> elementValuePairs;
    private String annotationType;

    public AnnotationEntry(int type_index, ConstantPool constant_pool, boolean isRuntimeVisible) {
        this.typeIndex = type_index;
        this.constantPool = constant_pool;
        this.isRuntimeVisible = isRuntimeVisible;
        this.annotationType = ((ConstantUtf8) constantPool.getConstant(typeIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public void addElementNameValuePair(ElementValuePair elementNameValuePair) {
        elementValuePairs.add(elementNameValuePair);
    }

    public String getAnnotationType() {
        return annotationType;
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

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    public String toShortString() {
        StringBuilder result = new StringBuilder();
        result.append("@");
        result.append(getAnnotationType());
        ElementValuePair[] evPairs = getElementValuePairs();
        if (evPairs.length > 0) {
            result.append("(");
            for (ElementValuePair element : evPairs) {
                result.append(element.toShortString());
            }
            result.append(")");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return toShortString();
    }

    public static AnnotationEntry[] createAnnotationEntries(Attribute[] attrs) {
        // Find attributes that contain annotation data
        List<AnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (Attribute attribute : attrs) {
            if (attribute instanceof Annotations) {
                Annotations runtimeAnnotations = (Annotations) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getAnnotationEntries());
            }
        }
        return accumulatedAnnotations.toArray(new AnnotationEntry[accumulatedAnnotations.size()]);
    }

    public static AnnotationEntry read(DataInput input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
        AnnotationEntry annotationEntry = new AnnotationEntry(input.readUnsignedShort(), constant_pool, isRuntimeVisible);
        int num_element_value_pairs = input.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList<>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            annotationEntry.elementValuePairs.add(new ElementValuePair(input.readUnsignedShort(), ElementValue.readElementValue(input, constant_pool), constant_pool));
        }
        return annotationEntry;
    }
}
