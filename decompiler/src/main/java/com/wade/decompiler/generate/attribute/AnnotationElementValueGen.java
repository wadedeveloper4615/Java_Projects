package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.element.AnnotationElementValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class AnnotationElementValueGen extends ElementValueGen {
    private AnnotationEntryGen annotationEntry;

    public AnnotationElementValueGen(byte type, AnnotationElementValue value) {
        super(type);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException("Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.annotationEntry = new AnnotationEntryGen(value.getAnnotationEntry());
    }
}
