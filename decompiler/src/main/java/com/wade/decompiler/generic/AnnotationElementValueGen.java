package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.AnnotationElementValue;
import com.wade.decompiler.classfile.ElementValue;

public class AnnotationElementValueGen extends ElementValueGen {
    // For annotation element values, this is the annotation
    private final AnnotationEntryGen a;

    public AnnotationElementValueGen(final AnnotationElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        super(ANNOTATION, cpool);
        a = new AnnotationEntryGen(value.getAnnotationEntry(), cpool, copyPoolEntries);
    }

    public AnnotationElementValueGen(final AnnotationEntryGen a, final ConstantPoolGen cpool) {
        super(ANNOTATION, cpool);
        this.a = a;
    }

    public AnnotationElementValueGen(final int type, final AnnotationEntryGen annotation, final ConstantPoolGen cpool) {
        super(type, cpool);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.a = annotation;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType()); // u1 type of value (ANNOTATION == '@')
        a.dump(dos);
    }

    public AnnotationEntryGen getAnnotation() {
        return a;
    }

    @Override
    public ElementValue getElementValue() {
        return new AnnotationElementValue(super.getElementValueType(), a.getAnnotation(), getConstantPool().getConstantPool());
    }

    @Override
    public String stringifyValue() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
