package com.wade.app;

import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;

public class MethodInfo {
    private String name;
    private String signature;
    private String access;

	public MethodInfo(ConstantPool constantPool,Method method) {
        access = Utility.accessToString(method.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        signature = Utility.signatureToString(method.getSignature());
        name = method.getName();
	}
}
