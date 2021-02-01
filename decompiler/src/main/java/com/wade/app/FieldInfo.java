package com.wade.app;

import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.Utility;

public class FieldInfo {
    private String name;
    private String signature;
    private String access;

    public FieldInfo(ConstantPool constantPool,Field field) {
        access = Utility.accessToString(field.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        signature = Utility.signatureToString(field.getSignature());
        name = field.getName();
	}
}
