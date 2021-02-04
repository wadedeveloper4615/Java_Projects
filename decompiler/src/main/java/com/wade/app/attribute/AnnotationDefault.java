package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (ElementValue) null, constant_pool);
        defaultValue = ElementValue.readElementValue(input, constant_pool);
    }

    public AnnotationDefault(final int name_index, final int length, final ElementValue defaultValue, final ConstantPool constant_pool) {
        super(Const.ATTR_ANNOTATION_DEFAULT, name_index, length, constant_pool);
        this.defaultValue = defaultValue;
    }

    public final ElementValue getDefaultValue() {
        return defaultValue;
    }

    public final void setDefaultValue(final ElementValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
