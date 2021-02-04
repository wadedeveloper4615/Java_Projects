package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Objects;

import com.wade.app.Const;
import com.wade.app.attribute.Attribute;
import com.wade.app.attribute.ConstantValue;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.exception.ClassFormatException;
import com.wade.app.util.Utility;

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

    public Field(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public Field(final Field c) {
        super(c);
    }

    public Field(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    public ConstantValue getConstantValue() {
        for (final Attribute attribute : super.getAttributes()) {
            if (attribute.getTag() == Const.ATTR_CONSTANT_VALUE) {
                return (ConstantValue) attribute;
            }
        }
        return null;
    }

    public Type getType() throws ClassFormatException {
        return Type.getReturnType(getSignature());
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    /**
     * Return string representation close to declaration format, `public static
     * final short MAX = 100', e.g..
     *
     * @return String representation of field, including the signature.
     */
    @Override
    public String toString() {
        String name;
        String signature;
        String access; // Short cuts to constant pool

        // Get names from constant pool
        access = Utility.accessToString(super.getAccessFlags());
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

    /**
     * @return Comparison strategy object
     */
    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    /**
     * @param comparator Comparison strategy object
     */
    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
