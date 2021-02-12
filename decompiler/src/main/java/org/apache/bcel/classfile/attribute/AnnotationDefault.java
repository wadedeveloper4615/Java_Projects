
package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.ElementValue;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public class AnnotationDefault extends Attribute {

    private ElementValue defaultValue;

    public AnnotationDefault(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (ElementValue) null, constant_pool);
        defaultValue = ElementValue.readElementValue(input, constant_pool);
    }

    public AnnotationDefault(final int name_index, final int length, final ElementValue defaultValue, final ConstantPool constant_pool) {
        super(Const.ATTR_ANNOTATION_DEFAULT, name_index, length, constant_pool);
        this.defaultValue = defaultValue;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitAnnotationDefault(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public final void dump(final DataOutputStream dos) throws IOException {
        super.dump(dos);
        defaultValue.dump(dos);
    }

    public final ElementValue getDefaultValue() {
        return defaultValue;
    }

    public final void setDefaultValue(final ElementValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
