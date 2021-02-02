package com.wade.app;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.LocalVariable;
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

    private boolean isConstructor;

    private String constructorName;

    private Code code;

    public MethodInfo(ConstantPool constantPool, Method method, String constructorName) {
        access = Utility.accessToString(method.getAccessFlags());
        signature = ((ConstantUtf8) method.getConstantPool().getConstant(method.getSignatureIndex(), Const.CONSTANT_Utf8)).getBytes();
        name = ((ConstantUtf8) method.getConstantPool().getConstant(method.getNameIndex(), Const.CONSTANT_Utf8)).getBytes();
        this.constructorName = constructorName;
        isConstructor = name.contains("<init>");
        localVariables = method.getLocalVariableTable();
        code = method.getCode();
//        Attribute[] attributes = method.getAttributes();
//        for (Attribute attr : attributes) {
//            System.out.println(attr.toString());
//        }
        exceptionTable = method.getExceptionTable();
    }

    private String methodSignatureToString(String signature, String name, String access, boolean chopit, LocalVariableTable vars) {
        String type = "";
        final StringBuilder buf = new StringBuilder("(");
        int var_index = access.contains("static") ? 0 : 1;
        try {
            int index = signature.indexOf('(') + 1;
            if (index <= 0) {
                throw new ClassFormatException("Invalid method signature: " + signature);
            }
            while (signature.charAt(index) != ')') {
                String param_type = typeSignatureToString(signature.substring(index), chopit, index);
                buf.append(param_type);
                if (vars != null) {
                    final LocalVariable l = vars.getLocalVariable(var_index, 0);
                    if (l != null) {
                        buf.append(" ").append(l.getName());
                    }
                } else {
                    buf.append(" arg").append(var_index);
                }
                if ("double".equals(param_type) || "long".equals(param_type)) {
                    var_index += 2;
                } else {
                    var_index++;
                }
                buf.append(", ");
                index++;
            }
            index++;
            type = typeSignatureToString(signature.substring(index), chopit, index);
        } catch (final StringIndexOutOfBoundsException e) { // Should never occur
            throw new ClassFormatException("Invalid method signature: " + signature, e);
        }
        if (buf.length() > 1) {
            buf.setLength(buf.length() - 2);
        }
        buf.append(")");
        if (isConstructor) {
            name = constructorName;
            type = "";
        } else {
            type = " " + type;
        }
        return access + type + " " + name + buf.toString();
    }

    @Override
    public String toString() {
        String temp = methodSignatureToString(signature, name, access, true, localVariables);
        final StringBuilder buf = new StringBuilder().append("\t").append(temp);
        buf.append("{\n");
        buf.append("\t/*\n");
        buf.append(code.toString2());
        buf.append("\n\t*/\n");
        buf.append("\n\t}\n");
        if (exceptionTable != null) {
            final String str = exceptionTable.toString();
            if (!str.isEmpty()) {
                buf.append("\n\t\tthrows ").append(str);
            }
        }
        return buf.toString();
    }

    private String typeSignatureToString(String signature, boolean chopit, int index) {
        switch (signature.charAt(0)) {
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            case 'F':
                return "float";
            case 'I':
                return "int";
            case 'J':
                return "long";
            case 'S':
                return "short";
            case 'Z':
                return "boolean";
            case 'V':
                return "void";
        }
        return null;
    }

}
