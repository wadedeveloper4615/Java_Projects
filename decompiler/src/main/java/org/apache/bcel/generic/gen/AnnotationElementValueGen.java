package org.apache.bcel.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.element.AnnotationElementValue;
import org.apache.bcel.classfile.element.ElementValue;

public class AnnotationElementValueGen extends ElementValueGen {
    private final AnnotationEntryGen a;

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

    public AnnotationElementValueGen(final AnnotationElementValue value, final ConstantPoolGen cpool, final boolean copyPoolEntries) {
        super(ANNOTATION, cpool);
        a = new AnnotationEntryGen(value.getAnnotationEntry(), cpool, copyPoolEntries);
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeByte(super.getElementValueType());
        a.dump(dos);
    }

    @Override
    public String stringifyValue() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public ElementValue getElementValue() {
        return new AnnotationElementValue(super.getElementValueType(), a.getAnnotation(), getConstantPool().getConstantPool());
    }

    public AnnotationEntryGen getAnnotation() {
        return a;
    }
}
