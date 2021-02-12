package org.apache.bcel.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import org.apache.bcel.classfile.annotations.ParameterAnnotationEntry;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.Code;
import org.apache.bcel.classfile.attribute.ExceptionTable;
import org.apache.bcel.classfile.attribute.LineNumberTable;
import org.apache.bcel.classfile.attribute.LocalVariableTable;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.compare.MethdComparator;
import org.apache.bcel.exceptions.ClassFormatException;
import org.apache.bcel.generic.Type;
import org.apache.bcel.util.BCELComparator;

public class Method extends FieldOrMethod {
    private static BCELComparator bcelComparator = new MethdComparator();
    private ParameterAnnotationEntry[] parameterAnnotationEntries;

    public Method() {
    }

    public Method(DataInputStream file, ConstantPool constantPool) throws IOException, ClassFormatException {
        super(file, constantPool);
    }

    public Method(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) {
        super(accessFlags, nameIndex, signatureIndex, attributes, constantPool);
    }

    public Method(Method c) {
        super(c);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMethod(this);
    }

    public Method copy(ConstantPool _constantPool) {
        return (Method) copy_(_constantPool);
    }

    @Override
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(getSignature());
    }

    public Code getCode() {
        for (Attribute attribute : super.getAttributes()) {
            if (attribute instanceof Code) {
                return (Code) attribute;
            }
        }
        return null;
    }

    public BCELComparator getComparator() {
        return bcelComparator;
    }

    public ExceptionTable getExceptionTable() {
        for (Attribute attribute : super.getAttributes()) {
            if (attribute instanceof ExceptionTable) {
                return (ExceptionTable) attribute;
            }
        }
        return null;
    }

    public LineNumberTable getLineNumberTable() {
        Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLineNumberTable();
    }

    public LocalVariableTable getLocalVariableTable() {
        Code code = getCode();
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

    public void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }

    @Override
    public String toString() {
        String access = Utility.accessToString(super.getAccessFlags());
        String signature = getSignature();
        String name = getName();
        signature = Utility.methodSignatureToString(signature, name, access, true, getLocalVariableTable());
        StringBuilder buf = new StringBuilder(signature);
        for (Attribute attribute : super.getAttributes()) {
            if (!((attribute instanceof Code) || (attribute instanceof ExceptionTable))) {
                buf.append(" [").append(attribute).append("]");
            }
        }
        ExceptionTable e = getExceptionTable();
        if (e != null) {
            String str = e.toString();
            if (!str.isEmpty()) {
                buf.append("\n\t\tthrows ").append(str);
            }
        }
        return buf.toString();
    }
}
