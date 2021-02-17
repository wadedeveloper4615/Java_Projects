package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.util.BCELComparator;

public final class Method extends FieldOrMethod {
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(final Object o1, final Object o2) {
            final Method THIS = (Method) o1;
            final Method THAT = (Method) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(final Object o) {
            final Method THIS = (Method) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };
    // annotations defined on the parameters of a method
    private ParameterAnnotationEntry[] parameterAnnotationEntries;

    public Method() {
    }

    Method(final DataInput file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public Method(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    public Method(final Method c) {
        super(c);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitMethod(this);
    }

    public Method copy(final ConstantPool _constant_pool) {
        return (Method) copy_(_constant_pool);
    }

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(getSignature());
    }

    public Code getCode() {
        for (final Attribute attribute : super.getAttributes()) {
            if (attribute instanceof Code) {
                return (Code) attribute;
            }
        }
        return null;
    }

    public ExceptionTable getExceptionTable() {
        for (final Attribute attribute : super.getAttributes()) {
            if (attribute instanceof ExceptionTable) {
                return (ExceptionTable) attribute;
            }
        }
        return null;
    }

    public LineNumberTable getLineNumberTable() {
        final Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLineNumberTable();
    }

    public LocalVariableTable getLocalVariableTable() {
        final Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLocalVariableTable();
    }

    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        if (parameterAnnotationEntries == null) {
            parameterAnnotationEntries = ParameterAnnotationEntry.createParameterAnnotationEntries(getAttributes());
        }
        return parameterAnnotationEntries;
    }

    public Type getReturnType() {
        return Type.getReturnType(getSignature());
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    @Override
    public String toString() {
        final String access = Utility.accessToString(new ClassAccessFlagsList(super.getFlags()));
        // Get name and signature from constant pool
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(super.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
        String signature = c.getBytes();
        c = (ConstantUtf8) super.getConstantPool().getConstant(super.getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
        final String name = c.getBytes();
        signature = Utility.methodSignatureToString(signature, name, access, true, getLocalVariableTable());
        final StringBuilder buf = new StringBuilder(signature);
        for (final Attribute attribute : super.getAttributes()) {
            if (!((attribute instanceof Code) || (attribute instanceof ExceptionTable))) {
                buf.append(" [").append(attribute).append("]");
            }
        }
        final ExceptionTable e = getExceptionTable();
        if (e != null) {
            final String str = e.toString();
            if (!str.isEmpty()) {
                buf.append("\n\t\tthrows ").append(str);
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
