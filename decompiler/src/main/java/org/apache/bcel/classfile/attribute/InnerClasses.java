package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileAttributes;

public final class InnerClasses extends Attribute {
    private InnerClass[] innerClasses;

    public InnerClasses(final InnerClasses c) {
        this(c.getNameIndex(), c.getLength(), c.getInnerClasses(), c.getConstantPool());
    }

    public InnerClasses(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (InnerClass[]) null, constant_pool);
        final int number_of_classes = input.readUnsignedShort();
        innerClasses = new InnerClass[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            innerClasses[i] = new InnerClass(input);
        }
    }

    public InnerClasses(final int name_index, final int length, final InnerClass[] innerClasses, final ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_INNER_CLASSES, name_index, length, constant_pool);
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }

    @Override
    public void accept(final Visitor v) {
        v.visitInnerClasses(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        // TODO this could be recoded to use a lower level constructor after creating a
        // copy of the inner classes
        final InnerClasses c = (InnerClasses) clone();
        c.innerClasses = new InnerClass[innerClasses.length];
        for (int i = 0; i < innerClasses.length; i++) {
            c.innerClasses[i] = innerClasses[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(innerClasses.length);
        for (final InnerClass inner_class : innerClasses) {
            inner_class.dump(file);
        }
    }

    public InnerClass[] getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(final InnerClass[] innerClasses) {
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("InnerClasses(");
        buf.append(innerClasses.length);
        buf.append("):\n");
        for (final InnerClass inner_class : innerClasses) {
            buf.append(inner_class.toString(super.getConstantPool())).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
