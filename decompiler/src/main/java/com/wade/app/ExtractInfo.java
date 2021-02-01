package com.wade.app;

import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;

public class ExtractInfo {
	private String className;
	private String accessFlags;
	private String type;
	private String superClass;
	private ConstantPool constantPool;
	private String version;
	private String[] interfaces;
	private FieldInfo[] fields;
	private MethodInfo[] methods;

	public ExtractInfo() {
	}

	public ExtractInfo(JavaClass java_class) throws ClassNotFoundException {
		if (java_class != null) {
			className = java_class.getClassName();
			accessFlags = Utility.accessToString(java_class.getAccessFlags());
			;
			if (java_class.isClass())
				type = "class";
			else if (java_class.isInterface())
				type = "interface";
			else if (java_class.isEnum())
				type = "enum";
			version = java_class.getMajor() + "." + java_class.getMinor();
			constantPool = java_class.getConstantPool();
			superClass = extractClassNameString(java_class.getSuperclassNameIndex());
			interfaces = extractInterfaces(java_class.getInterfaceIndices());
			fields = extractFields(java_class.getFields());
			methods = extractMethods(java_class.getMethods());
		}
	}

	private FieldInfo[] extractFields(Field[] fields) {
		FieldInfo[] result = new FieldInfo[fields.length];
		for (int i = 0; i < fields.length; i++) {
			result[i] = new FieldInfo(constantPool, fields[i]);
		}
		return result;
	}

	private MethodInfo[] extractMethods(Method[] methods) {
		MethodInfo[] result = new MethodInfo[methods.length];
		for (int i = 0; i < methods.length; i++) {
			result[i] = new MethodInfo(constantPool, methods[i]);
		}
		return result;
	}

	private String[] extractInterfaces(int[] interfaces) throws ClassNotFoundException {
		String[] result = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++) {
			result[i] = extractClassNameString(interfaces[i]);
		}
		return result;
	}

	private String extractClassNameString(int index) {
		ConstantClass constantClass = (ConstantClass) constantPool.getConstant(index);
		ConstantUtf8 constant = (ConstantUtf8) constantPool.getConstant(constantClass.getNameIndex());
		return constant.getBytes().replace('/', '.');
	}

	public void out() {
		System.out.println("/* version : " + version + " */");
		System.out.print(accessFlags + " " + type + " " + className + " extends " + superClass);
		if (interfaces.length > 0) {
			System.out.print(" implements ");
			for (int i = 0; i < interfaces.length; i++) {
				System.out.print(interfaces[i] + "");
				if (i < interfaces.length - 1) {
					System.out.print(",");
				}
			}
			System.out.println(" {");
		} else {
			System.out.println(" {");
		}
		System.out.println("}");
	}
}
