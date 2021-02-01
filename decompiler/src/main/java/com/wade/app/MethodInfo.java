package com.wade.app;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;

public class MethodInfo {
    private String name;

    private String signature;

    private String access;
    private LocalVariableTable localVariables;
    private Attribute[] attributes;

    private ExceptionTable exceptionTable;

    public MethodInfo(ConstantPool constantPool, Method method) {
        access = Utility.accessToString(method.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        signature = ((ConstantUtf8) method.getConstantPool().getConstant(method.getSignatureIndex(), Const.CONSTANT_Utf8)).getBytes();
        name = ((ConstantUtf8) method.getConstantPool().getConstant(method.getNameIndex(), Const.CONSTANT_Utf8)).getBytes();
        localVariables = method.getLocalVariableTable();
        attributes = method.getAttributes();
        exceptionTable = method.getExceptionTable();
    }

    @Override
    public String toString() {
        signature = Utility.methodSignatureToString(signature, name, access, true, localVariables);
        final StringBuilder buf = new StringBuilder().append("\t").append(signature);
        for (final Attribute attribute : attributes) {
            if (!((attribute instanceof Code) || (attribute instanceof ExceptionTable))) {
                buf.append(" [").append(attribute).append("]");
            }
        }
        if (exceptionTable != null) {
            final String str = exceptionTable.toString();
            if (!str.isEmpty()) {
                buf.append("\n\t\tthrows ").append(str);
            }
        }
        return buf.toString();
    }

}
