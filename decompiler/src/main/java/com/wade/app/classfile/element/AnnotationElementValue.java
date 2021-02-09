package com.wade.app.classfile.element;

import com.wade.app.classfile.attribute.AnnotationEntry;
import com.wade.app.classfile.attribute.ElementValue;
import com.wade.app.constantpool.ConstantPool;

public class AnnotationElementValue extends ElementValue {
    private AnnotationEntry annotationEntry;

    public AnnotationElementValue(int type, AnnotationEntry annotationEntry, ConstantPool cpool) {
        super(type, cpool);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.annotationEntry = annotationEntry;
    }

    public AnnotationEntry getAnnotationEntry() {
        return annotationEntry;
    }

    @Override
    public String toString() {
        return "AnnotationElementValue [annotationEntry=" + annotationEntry + "]";
    }

}
