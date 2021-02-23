package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.AnnotationDefault;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class AnnotationDefaultGen extends AttributeGen {
    private ElementValueGen defaultValue;

    public AnnotationDefaultGen(AnnotationDefault attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        defaultValue = ElementValueGen.readElementValue(attribute.getDefaultValue(), constantPool);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnnotationDefaultGen other = (AnnotationDefaultGen) obj;
        if (defaultValue == null) {
            if (other.defaultValue != null)
                return false;
        } else if (!defaultValue.equals(other.defaultValue))
            return false;
        return true;
    }

    public ElementValueGen getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AnnotationDefaultGen [defaultValue=" + defaultValue + "]";
    }
}
