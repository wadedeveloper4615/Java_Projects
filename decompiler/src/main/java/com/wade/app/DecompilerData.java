package com.wade.app;

import java.util.List;

import com.wade.app.classfile.ClassFileName;
import com.wade.app.classfile.FieldVariable;
import com.wade.app.classfile.JavaClass;
import com.wade.app.classfile.MethodCode;
import com.wade.app.classfile.attribute.Attribute;
import com.wade.app.classfile.attribute.Code;
import com.wade.app.classfile.attribute.ConstantValue;
import com.wade.app.classfile.attribute.ExceptionTable;
import com.wade.app.classfile.attribute.LineNumberTable;
import com.wade.app.classfile.attribute.LocalVariableTable;
import com.wade.app.enums.ClassAccessFlags;
import com.wade.app.enums.ClassFileAttributes;
import com.wade.app.util.ClassAccessFlagsList;
import com.wade.app.util.FieldsList;
import com.wade.app.util.InterfacesList;
import com.wade.app.util.MethodsList;
import com.wade.app.util.Utility;

public class DecompilerData {
    private String className;
    private String superClassName;
    private String accessString;
    private String type;
    private JavaClass javaClass;
    private String packageName;
    private String[] interfaces;

    public DecompilerData(JavaClass javaClass) {
        this.javaClass = javaClass;
        List<ClassAccessFlags> list = javaClass.getAccessFlags().getList();
        this.accessString = accessToString(list.toArray(new ClassAccessFlags[list.size()]), true);
        this.type = classOrInterface(javaClass.getAccessFlags());
        this.className = transform(javaClass.getClassName());
        this.packageName = getPackage(javaClass.getClassName());
        this.superClassName = transform(javaClass.getSuperclassName());
        this.interfaces = transform(javaClass.getInterfaces());
    }

    public String accessToString(ClassAccessFlags[] accessFlags) {
        return accessToString(accessFlags, false);
    }

    private String accessToString(ClassAccessFlags[] accessFlags, boolean forAClass) {
        StringBuilder buf = new StringBuilder();
        for (ClassAccessFlags flag : accessFlags) {
            boolean allowed = (flag == ClassAccessFlags.ACC_SUPER) || flag == ClassAccessFlags.ACC_INTERFACE;
            if (forAClass && allowed) {
                continue;
            }
            buf.append(flag.getName() + " ");
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

    public String compactClassName(String str) {
        return compactClassName(str, true);
    }

    public String compactClassName(String str, boolean chopit) {
        return compactClassName(str, "java.lang.", chopit);
    }

    public String compactClassName(String str, String prefix, boolean chopit) {
        int len = prefix.length();
        str = str.replace('/', '.');
        if (chopit) {
            if (str.startsWith(prefix) && (str.substring(len).indexOf('.') == -1)) {
                str = str.substring(len);
            }
        }
        return str;
    }

    public Code getCode(MethodCode method) {
        for (Attribute attribute : method.getAttributes()) {
            if (attribute instanceof Code) {
                return (Code) attribute;
            }
        }
        return null;
    }

    public ConstantValue getConstantValue(FieldVariable field) {
        for (Attribute attribute : field.getAttributes()) {
            if (attribute.getTag() == ClassFileAttributes.ATTR_CONSTANT_VALUE) {
                return (ConstantValue) attribute;
            }
        }
        return null;
    }

    public ExceptionTable getExceptionTable(MethodCode method) {
        for (Attribute attribute : method.getAttributes()) {
            if (attribute instanceof ExceptionTable) {
                return (ExceptionTable) attribute;
            }
        }
        return null;
    }

    public LineNumberTable getLineNumberTable(MethodCode method) {
        Code code = getCode(method);
        if (code == null) {
            return null;
        }
        return code.getLineNumberTable();
    }

    public LocalVariableTable getLocalVariableTable(MethodCode method) {
        Code code = getCode(method);
        if (code == null) {
            return null;
        }
        return code.getLocalVariableTable();
    }

    private String getPackage(ClassFileName classFileName) {
        String name = classFileName.getName().replace('/', '.');
        name = name.substring(0, name.lastIndexOf('.'));
        return name;
    }

    public void output() {
        System.out.println("/*");
        System.out.println(javaClass.toString());
        System.out.println("*/");
        System.out.print("package " + packageName + ";\n\n");
        System.out.print(accessString + " ");
        System.out.print(type + " ");
        System.out.print(className);
        System.out.print(" extends ");
        System.out.print(superClassName);
        outputInterfaces(interfaces);
        System.out.println(" {");
        outputFields(javaClass.getFields());
        System.out.println();
        outputMethods(className, javaClass.getMethods());
        System.out.println("}");
    }

    private void outputFields(FieldsList fields) {
        for (FieldVariable field : fields.getFields()) {
            String access = Utility.accessToString(field.getAccessFlags(), false);
            String signature = Utility.signatureToString(field.getSignature());
            ConstantValue cv = getConstantValue(field);
            String value = "";
            if (cv != null) {
                value = " = " + cv.convert();
            }
            System.out.println("\t" + access + signature + " " + field.getName() + value + ";");
        }
    }

    private void outputInterfaces(String[] interfaces) {
        if (interfaces.length >= 1) {
            System.out.print(" implements ");
            int index = 0;
            for (String name : interfaces) {
                System.out.print(name);
                if (index < interfaces.length - 1) {
                    System.out.print(",");
                }
                index++;
            }
        }
    }

    private void outputMethods(String className, MethodsList methods) {
        for (MethodCode method : methods.getMethods()) {
            String name = method.getName();
            String signature = method.getSignature();
            String access = accessToString(method.getAccessFlags());
            signature = Utility.methodSignatureToString(signature, name, className, access, true, getLocalVariableTable(method));
            System.out.print("\t" + signature);
            System.out.println("{");
            System.out.println("\t}\n");
        }
    }

    private String transform(ClassFileName classFileName) {
        String name = classFileName.getName().replace('/', '.');
        name = name.substring(name.lastIndexOf('.') + 1);
        return name;
    }

    private String[] transform(InterfacesList interfaces) {
        String[] returnValue = new String[interfaces.getInterfaces().length];
        int index = 0;
        for (ClassFileName c : interfaces.getInterfaces()) {
            returnValue[index] = c.getName().replace('/', '.');
            index++;
        }
        return returnValue;
    }

}
