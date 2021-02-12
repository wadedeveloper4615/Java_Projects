
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.bcel.enums.ClassFileConstants;

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

    @Override
    public void accept(final Visitor v) {
        v.visitAnnotationEntry(this);
    }

    public void addElementNameValuePair(final ElementValuePair elementNameValuePair) {
        elementValuePairs.add(elementNameValuePair);
    }

    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeShort(typeIndex);
        dos.writeShort(elementValuePairs.size());
        for (ElementValuePair envp : elementValuePairs) {
            envp.dump(dos);
        }
    }

    public String getAnnotationType() {
        return ((ConstantUtf8) constantPool.getConstant(typeIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public int getAnnotationTypeIndex() {
        return typeIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ElementValuePair[] getElementValuePairs() {
        // TODO return List
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

    public String toShortString() {
        final StringBuilder result = new StringBuilder();
        result.append("@");
        result.append(getAnnotationType());
        final ElementValuePair[] evPairs = getElementValuePairs();
        if (evPairs.length > 0) {
            result.append("(");
            for (final ElementValuePair element : evPairs) {
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

    public static AnnotationEntry read(final DataInput input, final ConstantPool constant_pool, final boolean isRuntimeVisible) throws IOException {

        final AnnotationEntry annotationEntry = new AnnotationEntry(input.readUnsignedShort(), constant_pool, isRuntimeVisible);
        final int num_element_value_pairs = input.readUnsignedShort();
        annotationEntry.elementValuePairs = new ArrayList<>();
        for (int i = 0; i < num_element_value_pairs; i++) {
            annotationEntry.elementValuePairs.add(new ElementValuePair(input.readUnsignedShort(), ElementValue.readElementValue(input, constant_pool), constant_pool));
        }
        return annotationEntry;
    }
}
