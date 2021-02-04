package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.constantpool.Node;
import com.wade.app.exception.ClassFormatException;

public class AnnotationEntry implements Node {
    private final int typeIndex;
    private final ConstantPool constantPool;
    private final boolean isRuntimeVisible;

    private List<ElementValuePair> elementValuePairs;

    public AnnotationEntry(final int type_index, final ConstantPool constant_pool, final boolean isRuntimeVisible) {
        this.typeIndex = type_index;
        this.constantPool = constant_pool;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public void addElementNameValuePair(final ElementValuePair elementNameValuePair) {
        elementValuePairs.add(elementNameValuePair);
    }

    public String getAnnotationType() throws ClassFormatException {
        final ConstantUtf8 c = (ConstantUtf8) constantPool.getConstant(typeIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
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

    public final int getNumElementValuePairs() {
        return elementValuePairs.size();
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    public static AnnotationEntry[] createAnnotationEntries(final Attribute[] attrs) {
        // Find attributes that contain annotation data
        final List<AnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (final Attribute attribute : attrs) {
            if (attribute instanceof Annotations) {
                final Annotations runtimeAnnotations = (Annotations) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getAnnotationEntries());
            }
        }
        return accumulatedAnnotations.toArray(new AnnotationEntry[accumulatedAnnotations.size()]);
    }

    public static AnnotationEntry read(final DataInputStream input, final ConstantPool constant_pool, final boolean isRuntimeVisible) throws IOException {
        final AnnotationEntry annotationEntry = new AnnotationEntry(input.readUnsignedShort(), constant_pool, isRuntimeVisible);
        final int num_element_value_pairs = input.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList<>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            annotationEntry.elementValuePairs.add(new ElementValuePair(input.readUnsignedShort(), ElementValue.readElementValue(input, constant_pool), constant_pool));
        }
        return annotationEntry;
    }
}
