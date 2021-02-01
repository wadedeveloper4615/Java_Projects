package com.wade.app;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class Decompiler {
	public static void main(String[] argv) {
		try {
			for (int i = 0; i < argv.length; i++) {
				if (argv[i].endsWith(".class")) {
					JavaClass java_class = new ClassParser(argv[i]).parse();
					ExtractInfo info = new ExtractInfo(java_class);
					info.out();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
