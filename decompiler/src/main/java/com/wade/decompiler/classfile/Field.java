package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.BCELComparator;

public class Field extends FieldOrMethod {
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(Object o1, Object o2) {
            Field THIS = (Field) o1;
            Field THAT = (Field) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(Object o) {
            Field THIS = (Field) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };

    public Field(DataInput file, ConstantPool constantPool) throws IOException, ClassFormatException {
        super(file, constantPool);
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

    @Override
    public String toString() {
        String name;
        String signature;
        String access; // Short cuts to constant pool
        // Get names from constant pool
        access = Utility.accessToString(new ClassAccessFlagsList(super.getFlags()));
        access = access.isEmpty() ? "" : (access + " ");
        signature = Utility.signatureToString(getSignature());
        name = getName();
        StringBuilder buf = new StringBuilder(64); // CHECKSTYLE IGNORE MagicNumber
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

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
