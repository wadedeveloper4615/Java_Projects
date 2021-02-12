package org.apache.bcel.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantValue;
import org.apache.bcel.compare.FieldComparator;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.exceptions.ClassFormatException;
import org.apache.bcel.generic.Type;
import org.apache.bcel.util.BCELComparator;

public class Field extends FieldOrMethod {
    private BCELComparator bcelComparator = new FieldComparator();

    public Field(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public Field(Field c) {
        super(c);
    }

    public Field(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) {
        super(accessFlags, nameIndex, signatureIndex, attributes, constantPool);
    }

    @Override
    public void accept(Visitor v) {
        v.visitField(this);
    }

    public Field copy(ConstantPool _constant_pool) {
        return (Field) copy_(_constant_pool);
    }

    @Override
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public BCELComparator getComparator() {
        return bcelComparator;
    }

    public ConstantValue getConstantValue() {
        for (Attribute attribute : super.getAttributes()) {
            if (attribute.getTag() == ClassFileAttributes.ATTR_CONSTANT_VALUE) {
                return (ConstantValue) attribute;
            }
        }
        return null;
    }

    public Type getType() {
        return Type.getReturnType(getSignature());
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }

    @Override
    public String toString() {
        String access = Utility.accessToString(super.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        String signature = getSignature();
        String name = getName();
        StringBuilder buf = new StringBuilder(64);
        buf.append(access).append(signature).append(" ").append(name);
        ConstantValue cv = getConstantValue();
        if (cv != null) {
            buf.append(" = ").append(cv);
        }
        for (Attribute attribute : super.getAttributes()) {
            if (!(attribute instanceof ConstantValue)) {
                buf.append(" [").append(attribute).append("]");
            }
        }
        return buf.toString();
    }
}
