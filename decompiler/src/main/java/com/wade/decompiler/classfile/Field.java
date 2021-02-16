package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileAttributes;
import com.wade.decompiler.generic.Type;
import com.wade.decompiler.util.BCELComparator;

public final class Field extends FieldOrMethod {
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(final Object o1, final Object o2) {
            final Field THIS = (Field) o1;
            final Field THAT = (Field) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(final Object o) {
            final Field THIS = (Field) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };

    Field(final DataInput file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public Field(final Field c) {
        super(c);
    }

    public Field(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitField(this);
    }

    public Field copy(final ConstantPool _constant_pool) {
        return (Field) copy_(_constant_pool);
    }

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public ConstantValue getConstantValue() {
        for (final Attribute attribute : super.getAttributes()) {
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
        final StringBuilder buf = new StringBuilder(64); // CHECKSTYLE IGNORE MagicNumber
        buf.append(access).append(signature).append(" ").append(name);
        final ConstantValue cv = getConstantValue();
        if (cv != null) {
            buf.append(" = ").append(cv);
        }
        for (final Attribute attribute : super.getAttributes()) {
            if (!(attribute instanceof ConstantValue)) {
                buf.append(" [").append(attribute).append("]");
            }
        }
        return buf.toString();
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
