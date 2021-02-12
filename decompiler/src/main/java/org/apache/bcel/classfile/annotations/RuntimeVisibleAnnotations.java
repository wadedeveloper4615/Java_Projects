package org.apache.bcel.classfile.annotations;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.attribute.Annotations;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;

public class RuntimeVisibleAnnotations extends Annotations {
    public RuntimeVisibleAnnotations(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_RUNTIME_VISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, true);
    }

    @Override
    public Attribute copy(final ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public final void dump(final DataOutputStream dos) throws IOException {
        super.dump(dos);
        writeAnnotations(dos);
    }
}
