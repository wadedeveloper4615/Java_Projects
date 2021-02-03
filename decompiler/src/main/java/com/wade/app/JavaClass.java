package com.wade.app;

import com.wade.app.attribute.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.Node;
import com.wade.app.constantpool.SourceFile;
import com.wade.app.util.Utility;

public class JavaClass extends AccessFlags implements Cloneable, Node, Comparable<JavaClass> {
    public static final byte HEAP = 1;
    public static final byte FILE = 2;
    public static final byte ZIP = 3;
    private int classNameIndex;
    private int superclassNameIndex;
    private String fileName;
    private int major;
    private int minor;
    private ConstantPool constantPool;
    private int[] interfaces;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private byte source;
    private Object sourceFileName;
    private String className;
    private String packageName;
    private String superclassName;
    private String[] interfaceNames;

    public JavaClass(final int classNameIndex, final int superclassNameIndex, final String fileName, final int major, final int minor, final int access_flags, final ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, final byte source) throws ClassFormatException {
        super(access_flags);
        if (interfaces == null) {
            interfaces = new int[0];
        }
        if (attributes == null) {
            attributes = new Attribute[0];
        }
        if (fields == null) {
            fields = new Field[0];
        }
        if (methods == null) {
            methods = new Method[0];
        }
        this.classNameIndex = classNameIndex;
        this.superclassNameIndex = superclassNameIndex;
        this.fileName = fileName;
        this.major = major;
        this.minor = minor;
        this.constantPool = constantPool;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
        this.source = source;
        // Get source file name if available
        for (final Attribute attribute : attributes) {
            if (attribute instanceof SourceFile) {
                sourceFileName = ((SourceFile) attribute).getSourceFileName();
                break;
            }
        }
        /*
         * According to the specification the following entries must be of type
         * `ConstantClass' but we check that anyway via the `ConstPool.getConstant'
         * method.
         */
        className = constantPool.getConstantString(classNameIndex, Const.CONSTANT_Class);
        className = Utility.compactClassName(className, false);
        final int index = className.lastIndexOf('.');
        if (index < 0) {
            packageName = "";
        } else {
            packageName = className.substring(0, index);
        }
        if (superclassNameIndex > 0) {
            // May be zero -> class is java.lang.Object
            superclassName = constantPool.getConstantString(superclassNameIndex, Const.CONSTANT_Class);
            superclassName = Utility.compactClassName(superclassName, false);
        } else {
            superclassName = "java.lang.Object";
        }
        interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            final String str = constantPool.getConstantString(interfaces[i], Const.CONSTANT_Class);
            interfaceNames[i] = Utility.compactClassName(str, false);
        }
    }

    @Override
    public int compareTo(JavaClass obj) {
        return getClassName().compareTo(obj.getClassName());
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public String getClassName() {
        return className;
    }

    public int getClassNameIndex() {
        return classNameIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public Field[] getFields() {
        return fields;
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public int getMajor() {
        return major;
    }

    public Method[] getMethods() {
        return methods;
    }

    public int getMinor() {
        return minor;
    }

    public String getPackageName() {
        return packageName;
    }

    public byte getSource() {
        return source;
    }

    public Object getSourceFileName() {
        return sourceFileName;
    }

    public String getSuperclassName() {
        return superclassName;
    }

    public int getSuperclassNameIndex() {
        return superclassNameIndex;
    }

    public static byte getFile() {
        return FILE;
    }

    public static byte getHeap() {
        return HEAP;
    }

    public static byte getZip() {
        return ZIP;
    }
}
