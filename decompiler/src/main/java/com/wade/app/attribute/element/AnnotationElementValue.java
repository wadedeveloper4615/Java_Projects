package com.wade.app.attribute.element;

import com.wade.app.attribute.AnnotationEntry;
import com.wade.app.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class AnnotationElementValue extends ElementValue {
    private final AnnotationEntry annotationEntry;

    public AnnotationElementValue(final int type, final AnnotationEntry annotationEntry, final ConstantPool cpool) {
        super(type, cpool);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.annotationEntry = annotationEntry;
    }

    public AnnotationEntry getAnnotationEntry() {
        return annotationEntry;
    }
}
