package com.wade.app;

import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantValue;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.Utility;

public class FieldInfo {
    private String name;
    private String signature;
    private String access;
    private ConstantValue cv;

    public FieldInfo(ConstantPool constantPool, Field field) {
        access = Utility.accessToString(field.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        signature = Utility.signatureToString(field.getSignature());
        name = field.getName();
        cv = field.getConstantValue();
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder(64); // CHECKSTYLE IGNORE MagicNumber
        buf.append("\t").append(access).append(signature).append(" ").append(name);
        if (cv != null) {
            buf.append(" = ").append(cv);
        }
        return buf.toString();
    }
}
