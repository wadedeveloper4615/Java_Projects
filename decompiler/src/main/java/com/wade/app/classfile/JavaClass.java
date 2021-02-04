package com.wade.app.classfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.wade.app.AccessFlags;
import com.wade.app.Const;
import com.wade.app.Version;
import com.wade.app.attribute.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.Node;
import com.wade.app.constantpool.SourceFile;
import com.wade.app.exception.ClassFormatException;
import com.wade.app.util.ClassQueue;
import com.wade.app.util.Repository;
import com.wade.app.util.Utility;

public class JavaClass extends AbstractAccessFlags implements Node, Comparable<JavaClass> {
    public static byte HEAP = 1;
    public static byte FILE = 2;
    public static byte ZIP = 3;
    private int classNameIndex;
    private int superclassNameIndex;
    private String fileName;
    private Version version;
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

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, Version version, AccessFlags access_flags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte source) throws ClassFormatException {
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
        this.version = version;
        this.constantPool = constantPool;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
        this.source = source;
        for (Attribute attribute : attributes) {
            if (attribute instanceof SourceFile) {
                sourceFileName = ((SourceFile) attribute).getSourceFileName();
                break;
            }
        }
        className = constantPool.getConstantString(classNameIndex, Const.CONSTANT_Class);
        className = Utility.compactClassName(className, false);
        int index = className.lastIndexOf('.');
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
            String str = constantPool.getConstantString(interfaces[i], Const.CONSTANT_Class);
            interfaceNames[i] = Utility.compactClassName(str, false);
        }
    }

    @Override
    public int compareTo(JavaClass obj) {
        return getClassName().compareTo(obj.getClassName());
    }

    public JavaClass[] getAllInterfaces() throws ClassNotFoundException {
        ClassQueue queue = new ClassQueue();
        Set<JavaClass> allInterfaces = new TreeSet<>();
        queue.enqueue(this);
        while (!queue.empty()) {
            JavaClass clazz = queue.dequeue();
            JavaClass souper = clazz.getSuperClass();
            JavaClass[] _interfaces = clazz.getInterfacesClass();
            if (clazz.isInterface()) {
                allInterfaces.add(clazz);
            } else {
                if (souper != null) {
                    queue.enqueue(souper);
                }
            }
            for (JavaClass _interface : _interfaces) {
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
        String[] _interfaces = getInterfaceNames();
        JavaClass[] classes = new JavaClass[_interfaces.length];
        for (int i = 0; i < _interfaces.length; i++) {
            classes[i] = repository.loadClass(_interfaces[i]);
        }
        return classes;
    }

    public Method[] getMethods() {
        return methods;
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
        List<JavaClass> allSuperClasses = new ArrayList<>();
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

    public Version getVersion() {
        return version;
    }

    public boolean implementationOf(JavaClass inter) throws ClassNotFoundException {
        if (!inter.isInterface()) {
            throw new IllegalArgumentException(inter.getClassName() + " is no interface");
        }
        if (this.equals(inter)) {
            return true;
        }
        JavaClass[] super_interfaces = getAllInterfaces();
        for (JavaClass super_interface : super_interfaces) {
            if (super_interface.equals(inter)) {
                return true;
            }
        }
        return false;
    }

    public boolean instanceOf(JavaClass super_class) throws ClassNotFoundException {
        if (this.equals(super_class)) {
            return true;
        }
        JavaClass[] super_classes = getSuperClasses();
        for (JavaClass super_classe : super_classes) {
            if (super_classe.equals(super_class)) {
                return true;
            }
        }
        if (super_class.isInterface()) {
            return implementationOf(super_class);
        }
        return false;
    }

    public boolean isClass() {
        return !super.getAccessFlags().isInterface();
    }

    public boolean isSuper() {
        return super.getAccessFlags().isSuper();
    }

    public void setRepository(com.wade.app.util.Repository repository) {
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
