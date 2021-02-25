package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.element.AnnotationElementValue;

public class AnnotationElementValueGen extends ElementValueGen {
    private AnnotationEntryGen annotationEntry;

    public AnnotationElementValueGen(byte type, AnnotationElementValue value) {
        super(type);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.annotationEntry = new AnnotationEntryGen(value.getAnnotationEntry());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnnotationElementValueGen other = (AnnotationElementValueGen) obj;
        if (annotationEntry == null) {
            if (other.annotationEntry != null)
                return false;
        } else if (!annotationEntry.equals(other.annotationEntry))
            return false;
        return true;
    }

    public AnnotationEntryGen getAnnotationEntry() {
        return annotationEntry;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((annotationEntry == null) ? 0 : annotationEntry.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return annotationEntry.toString();
    }
}
