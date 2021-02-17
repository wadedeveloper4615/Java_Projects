package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Objects;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.ExceptionTable;
import com.wade.decompiler.classfile.attribute.LineNumberTable;
import com.wade.decompiler.classfile.attribute.LocalVariableTable;
import com.wade.decompiler.classfile.attribute.ParameterAnnotationEntry;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.BCELComparator;
import com.wade.decompiler.util.Utility;

public class Method extends FieldOrMethod {
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(Object o1, Object o2) {
            Method THIS = (Method) o1;
            Method THAT = (Method) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(Object o) {
            Method THIS = (Method) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };
    // annotations defined on the parameters of a method
    private ParameterAnnotationEntry[] parameterAnnotationEntries;

    public Method() {
    }

    public Method(DataInput file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        super(file, constant_pool);
    }

    public Method(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
    }

    public Method(Method c) {
        super(c);
    }

    @Override
    public void accept(Visitor v) {
        v.visitMethod(this);
    }

    public Method copy(ConstantPool _constant_pool) {
        return (Method) copy_(_constant_pool);
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

    @Override
    public String toString() {
        String access = Utility.accessToString(new ClassAccessFlagsList(super.getFlags()));
        // Get name and signature from constant pool
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(super.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
        String signature = c.getBytes();
        c = (ConstantUtf8) super.getConstantPool().getConstant(super.getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
        String name = c.getBytes();
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

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
