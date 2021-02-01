package com.wade.app;

import java.lang.reflect.Modifier;

import org.apache.bcel.classfile.JavaClass;

public class ExtractInfo {
	private String className;
	private String accessFlags;

	public ExtractInfo(JavaClass java_class) {
		className = java_class.getClassName();
		accessFlags = Modifier.toString(java_class.getAccessFlags());
	}
	
	public void out() {
		System.out.println(accessFlags+" "+className);
	}
}
