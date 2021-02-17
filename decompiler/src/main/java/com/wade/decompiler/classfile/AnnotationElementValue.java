package com.wade.decompiler.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

public class AnnotationElementValue extends ElementValue {
    // For annotation element values, this is the annotation
    private AnnotationEntry annotationEntry;

    public AnnotationElementValue(int type, AnnotationEntry annotationEntry, ConstantPool cpool) {
        super(type, cpool);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.annotationEntry = annotationEntry;
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        dos.writeByte(super.getType()); // u1 type of value (ANNOTATION == '@')
        annotationEntry.dump(dos);
    }

    public AnnotationEntry getAnnotationEntry() {
        return annotationEntry;
    }

    @Override
    public String stringifyValue() {
        return annotationEntry.toString();
    }

    @Override
    public String toString() {
        return stringifyValue();
    }
}
