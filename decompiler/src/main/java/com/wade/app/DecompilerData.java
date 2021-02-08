package com.wade.app;

import com.wade.app.classfile.ClassFileName;
import com.wade.app.classfile.JavaClass;
import com.wade.app.enums.ClassAccessFlags;
import com.wade.app.util.ClassAccessFlagsList;

public class DecompilerData {
    private String className;
    private String superClassName;
    private String accessString;
    private String type;

    public DecompilerData(JavaClass javaClass) {
        this.accessString = accessToString(javaClass.getAccessFlags());
        System.out.println(accessString);
        this.type = classOrInterface(javaClass.getAccessFlags());
        System.out.println(type);
        this.className = transform(javaClass.getClassName());
        System.out.println(className);
        this.superClassName = transform(javaClass.getSuperclassName());
        System.out.println(superClassName);
    }

    private String accessToString(ClassAccessFlagsList accessFlags) {
        final StringBuilder buf = new StringBuilder();
        for (ClassAccessFlags flags : accessFlags.getList()) {
            if (!(flags == ClassAccessFlags.ACC_OPEN || flags == ClassAccessFlags.ACC_SUPER || flags == ClassAccessFlags.ACC_SYNCHRONIZED || flags == ClassAccessFlags.ACC_TRANSITIVE)) {
                buf.append(flags.getName() + " ");
            }
        }
        return buf.toString().trim();
    }

    public String classOrInterface(ClassAccessFlagsList accessFlags) {
        boolean interfaceFound = false;
        for (ClassAccessFlags flags : accessFlags.getList()) {
            if (flags == ClassAccessFlags.ACC_INTERFACE) {
                interfaceFound = true;
            }
        }
        return interfaceFound ? "interface" : "class";
    }

    private String transform(ClassFileName classFileName) {
        String name = classFileName.getName().replace('/', '.');
        name = name.substring(name.lastIndexOf('.') + 1);
        return name;
    }
}
