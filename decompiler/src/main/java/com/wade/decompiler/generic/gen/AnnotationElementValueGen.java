package com.wade.decompiler.generic.gen;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.element.AnnotationElementValue;
import com.wade.decompiler.classfile.element.ElementValue;

public class AnnotationElementValueGen extends ElementValueGen {
    // For annotation element values, this is the annotation
    private AnnotationEntryGen a;

    public AnnotationElementValueGen(AnnotationElementValue value, ConstantPoolGen cpool, boolean copyPoolEntries) {
        super(ANNOTATION, cpool);
        a = new AnnotationEntryGen(value.getAnnotationEntry(), cpool, copyPoolEntries);
    }

    public AnnotationElementValueGen(AnnotationEntryGen a, ConstantPoolGen cpool) {
        super(ANNOTATION, cpool);
        this.a = a;
    }

    public AnnotationElementValueGen(int type, AnnotationEntryGen annotation, ConstantPoolGen cpool) {
        super(type, cpool);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.a = annotation;
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
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
