package com.wade.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.wade.app.attribute.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.Node;
import com.wade.app.constantpool.SourceFile;
import com.wade.app.exception.ClassFormatException;
import com.wade.app.util.ClassQueue;
import com.wade.app.util.Repository;
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
    private Repository repository;

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

    public JavaClass[] getAllInterfaces() throws ClassNotFoundException {
        final ClassQueue queue = new ClassQueue();
        final Set<JavaClass> allInterfaces = new TreeSet<>();
        queue.enqueue(this);
        while (!queue.empty()) {
            final JavaClass clazz = queue.dequeue();
            final JavaClass souper = clazz.getSuperClass();
            final JavaClass[] _interfaces = clazz.getInterfacesClass();
            if (clazz.isInterface()) {
                allInterfaces.add(clazz);
            } else {
                if (souper != null) {
                    queue.enqueue(souper);
                }
            }
            for (final JavaClass _interface : _interfaces) {
                queue.enqueue(_interface);
            }
        }
        return allInterfaces.toArray(new JavaClass[allInterfaces.size()]);
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

    public int[] getInterfaces() throws ClassNotFoundException {
        return interfaces;
    }

    public JavaClass[] getInterfacesClass() throws ClassNotFoundException {
        final String[] _interfaces = getInterfaceNames();
        final JavaClass[] classes = new JavaClass[_interfaces.length];
        for (int i = 0; i < _interfaces.length; i++) {
            classes[i] = repository.loadClass(_interfaces[i]);
        }
        return classes;
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

    public Repository getRepository() {
        return repository;
    }

    public byte getSource() {
        return source;
    }

    public Object getSourceFileName() {
        return sourceFileName;
    }

    public JavaClass getSuperClass() throws ClassNotFoundException {
        if ("java.lang.Object".equals(getClassName())) {
            return null;
        }
        return repository.loadClass(getSuperclassName());
    }

    public JavaClass[] getSuperClasses() throws ClassNotFoundException {
        JavaClass clazz = this;
        final List<JavaClass> allSuperClasses = new ArrayList<>();
        for (clazz = clazz.getSuperClass(); clazz != null; clazz = clazz.getSuperClass()) {
            allSuperClasses.add(clazz);
        }
        return allSuperClasses.toArray(new JavaClass[allSuperClasses.size()]);
    }

    public String getSuperclassName() {
        return superclassName;
    }

    public int getSuperclassNameIndex() {
        return superclassNameIndex;
    }

    public boolean implementationOf(final JavaClass inter) throws ClassNotFoundException {
        if (!inter.isInterface()) {
            throw new IllegalArgumentException(inter.getClassName() + " is no interface");
        }
        if (this.equals(inter)) {
            return true;
        }
        final JavaClass[] super_interfaces = getAllInterfaces();
        for (final JavaClass super_interface : super_interfaces) {
            if (super_interface.equals(inter)) {
                return true;
            }
        }
        return false;
    }

    public final boolean instanceOf(final JavaClass super_class) throws ClassNotFoundException {
        if (this.equals(super_class)) {
            return true;
        }
        final JavaClass[] super_classes = getSuperClasses();
        for (final JavaClass super_classe : super_classes) {
            if (super_classe.equals(super_class)) {
                return true;
            }
        }
        if (super_class.isInterface()) {
            return implementationOf(super_class);
        }
        return false;
    }

    public final boolean isClass() {
        return (super.getAccessFlags() & Const.ACC_INTERFACE) == 0;
    }

    public final boolean isSuper() {
        return (super.getAccessFlags() & Const.ACC_SUPER) != 0;
    }

    public void setRepository(final com.wade.app.util.Repository repository) {
        this.repository = repository;
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
