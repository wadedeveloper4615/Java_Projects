package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class InnerClasses extends Attribute {
    private InnerClass[] innerClasses;

    public InnerClasses(InnerClasses c) {
        this(c.getNameIndex(), c.getLength(), c.getInnerClasses(), c.getConstantPool());
    }

    public InnerClasses(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (InnerClass[]) null, constant_pool);
        int number_of_classes = input.readUnsignedShort();
        innerClasses = new InnerClass[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            innerClasses[i] = new InnerClass(input);
        }
    }

    public InnerClasses(int name_index, int length, InnerClass[] innerClasses, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_INNER_CLASSES, name_index, length, constant_pool);
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }

    @Override
    public void accept(Visitor v) {
        v.visitInnerClasses(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        InnerClasses c = (InnerClasses) clone();
        c.innerClasses = new InnerClass[innerClasses.length];
        for (int i = 0; i < innerClasses.length; i++) {
            c.innerClasses[i] = innerClasses[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(innerClasses.length);
        for (InnerClass inner_class : innerClasses) {
            inner_class.dump(file);
        }
    }

    public InnerClass[] getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(InnerClass[] innerClasses) {
        this.innerClasses = innerClasses != null ? innerClasses : new InnerClass[0];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("InnerClasses(");
        buf.append(innerClasses.length);
        buf.append("):\n");
        for (InnerClass inner_class : innerClasses) {
            buf.append(inner_class.toString(super.getConstantPool())).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
