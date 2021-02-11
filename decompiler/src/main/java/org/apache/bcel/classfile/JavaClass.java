package org.apache.bcel.classfile;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.bcel.ClassFileName;
import org.apache.bcel.Const;
import org.apache.bcel.JavaClassComparator;
import org.apache.bcel.enums.Version;
import org.apache.bcel.generic.Type;
import org.apache.bcel.util.BCELComparator;
import org.apache.bcel.util.ClassQueue;
import org.apache.bcel.util.SyntheticRepository;

public class JavaClass extends AccessFlags implements Cloneable, Node, Comparable<JavaClass> {
    public static byte HEAP = 1;
    public static byte FILE = 2;
    public static byte ZIP = 3;
    private static boolean debug = Boolean.getBoolean("JavaClass.debug");
    private BCELComparator bcelComparator = new JavaClassComparator();
    private String fileName;
    private String packageName;
    private String sourceFileName = "<Unknown>";
    private ClassFileName className;
    private ClassFileName superClassName;
    private Version version;
    private ConstantPool constantPool;
    private ClassFileName[] interfaces;
    private String[] interfaceNames;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private AnnotationEntry[] annotations;
    private byte source = HEAP;
    private boolean isAnonymous = false;
    private boolean isNested = false;
    private boolean computedNestedTypeStatus = false;
    private transient org.apache.bcel.util.Repository repository = SyntheticRepository.getInstance();

    public JavaClass(ClassFileName className, ClassFileName superclassName, String fileName, Version version, int access_flags, ConstantPool constantPool, ClassFileName[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes) {
        this(className, superclassName, fileName, version, access_flags, constantPool, interfaces, fields, methods, attributes, HEAP);
    }

    public JavaClass(ClassFileName className, ClassFileName superClassName, String fileName, Version version, int access_flags, ConstantPool constantPool, ClassFileName[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte source) {
        super(access_flags);
        if (interfaces == null) {
            interfaces = new ClassFileName[0];
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
        this.className = className;
        this.superClassName = superClassName;
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
        className.setName(Utility.compactClassName(className.getName(), false));
        int index = className.getName().lastIndexOf('.');
        if (index < 0) {
            packageName = "";
        } else {
            packageName = className.getName().substring(0, index);
        }
        if (superClassName.getNameIndex() > 0) {
            superClassName.setName(Utility.compactClassName(superClassName.getName(), false));
        } else {
            superClassName.setName("java.lang.Object");
            superClassName.setNameIndex(-1);

        }
        interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaceNames[i] = Utility.compactClassName(this.interfaces[i].getName(), false);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitJavaClass(this);
    }

    @Override
    public int compareTo(JavaClass obj) {
        return getClassName().getName().compareTo(obj.getClassName().getName());
    }

    private void computeNestedTypeStatus() {
        if (computedNestedTypeStatus) {
            return;
        }
        for (Attribute attribute : this.attributes) {
            if (attribute instanceof InnerClasses) {
                InnerClass[] innerClasses = ((InnerClasses) attribute).getInnerClasses();
                for (InnerClass innerClasse : innerClasses) {
                    boolean innerClassAttributeRefersToMe = false;
                    String inner_class_name = constantPool.getConstantString(innerClasse.getInnerClassIndex(), Const.CONSTANT_Class);
                    inner_class_name = Utility.compactClassName(inner_class_name, false);
                    if (inner_class_name.equals(getClassName().getName())) {
                        innerClassAttributeRefersToMe = true;
                    }
                    if (innerClassAttributeRefersToMe) {
                        this.isNested = true;
                        if (innerClasse.getInnerNameIndex() == 0) {
                            this.isAnonymous = true;
                        }
                    }
                }
            }
        }
        this.computedNestedTypeStatus = true;
    }

    public JavaClass copy() {
        JavaClass c = null;
        try {
            c = (JavaClass) clone();
            c.constantPool = constantPool.copy();
            c.interfaces = interfaces.clone();
            c.interfaceNames = interfaceNames.clone();
            c.fields = new Field[fields.length];
            for (int i = 0; i < fields.length; i++) {
                c.fields[i] = fields[i].copy(c.constantPool);
            }
            c.methods = new Method[methods.length];
            for (int i = 0; i < methods.length; i++) {
                c.methods[i] = methods[i].copy(c.constantPool);
            }
            c.attributes = new Attribute[attributes.length];
            for (int i = 0; i < attributes.length; i++) {
                c.attributes[i] = attributes[i].copy(c.constantPool);
            }
        } catch (CloneNotSupportedException e) {
        }
        return c;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeInt(Const.JVM_CLASSFILE_MAGIC);
        file.writeShort(version.getMinor());
        file.writeShort(version.getMajor());
        constantPool.dump(file);
        file.writeShort(super.getAccessFlags());
        file.writeShort(className.getNameIndex());
        file.writeShort(superClassName.getNameIndex());
        file.writeShort(interfaces.length);
        for (ClassFileName interface1 : interfaces) {
            file.writeShort(interface1.getNameIndex());
        }
        file.writeShort(fields.length);
        for (Field field : fields) {
            field.dump(file);
        }
        file.writeShort(methods.length);
        for (Method method : methods) {
            method.dump(file);
        }
        if (attributes != null) {
            file.writeShort(attributes.length);
            for (Attribute attribute : attributes) {
                attribute.dump(file);
            }
        } else {
            file.writeShort(0);
        }
        file.flush();
    }

    public void dump(File file) throws IOException {
        String parent = file.getParent();
        if (parent != null) {
            File dir = new File(parent);
            if (!dir.mkdirs()) { // either was not created or already existed
                if (!dir.isDirectory()) {
                    throw new IOException("Could not create the directory " + dir);
                }
            }
        }
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dump(dos);
        }
    }

    public void dump(OutputStream file) throws IOException {
        dump(new DataOutputStream(file));
    }

    public void dump(String _file_name) throws IOException {
        dump(new File(_file_name));
    }

    @Override
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public JavaClass[] getAllInterfaces() throws ClassNotFoundException {
        ClassQueue queue = new ClassQueue();
        Set<JavaClass> allInterfaces = new TreeSet<>();
        queue.enqueue(this);
        while (!queue.empty()) {
            JavaClass clazz = queue.dequeue();
            JavaClass souper = clazz.getSuperClass();
            JavaClass[] _interfaces = clazz.getInterfaceJavaClass();
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

    public AnnotationEntry[] getAnnotationEntries() {
        if (annotations == null) {
            annotations = AnnotationEntry.createAnnotationEntries(getAttributes());
        }

        return annotations;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public byte[] getBytes() {
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        DataOutputStream ds = new DataOutputStream(s);
        try {
            dump(ds);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ds.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return s.toByteArray();
    }

    public ClassFileName getClassName() {
        return className;
    }

    public BCELComparator getComparator() {
        return bcelComparator;
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

    public JavaClass[] getInterfaceJavaClass() throws ClassNotFoundException {
        String[] _interfaces = getInterfaceNames();
        JavaClass[] classes = new JavaClass[_interfaces.length];
        for (int i = 0; i < _interfaces.length; i++) {
            classes[i] = repository.loadClass(_interfaces[i]);
        }
        return classes;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public ClassFileName[] getInterfaces() {
        return interfaces;
    }

    public Method getMethod(java.lang.reflect.Method m) {
        for (Method method : methods) {
            if (m.getName().equals(method.getName()) && (m.getModifiers() == method.getModifiers()) && Type.getSignature(m).equals(method.getSignature())) {
                return method;
            }
        }
        return null;
    }

    public Method[] getMethods() {
        return methods;
    }

    public String getPackageName() {
        return packageName;
    }

    public org.apache.bcel.util.Repository getRepository() {
        return repository;
    }

    public byte getSource() {
        return source;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public JavaClass getSuperClass() throws ClassNotFoundException {
        if ("java.lang.Object".equals(getClassName().getName())) {
            return null;
        }
        return repository.loadClass(getSuperClassName().getName());
    }

    public JavaClass[] getSuperClasses() throws ClassNotFoundException {
        JavaClass clazz = this;
        List<JavaClass> allSuperClasses = new ArrayList<>();
        for (clazz = clazz.getSuperClass(); clazz != null; clazz = clazz.getSuperClass()) {
            allSuperClasses.add(clazz);
        }
        return allSuperClasses.toArray(new JavaClass[allSuperClasses.size()]);
    }

    public ClassFileName getSuperClassName() {
        return superClassName;
    }

    public Version getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
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

    public boolean isAnonymous() {
        computeNestedTypeStatus();
        return this.isAnonymous;
    }

    public boolean isClass() {
        return (super.getAccessFlags() & Const.ACC_INTERFACE) == 0;
    }

    public boolean isNested() {
        computeNestedTypeStatus();
        return this.isNested;
    }

    public boolean isSuper() {
        return (super.getAccessFlags() & Const.ACC_SUPER) != 0;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public void setClassName(ClassFileName className) {
        this.className = className;
    }

    public void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public void setInterfaces(ClassFileName[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    public void setRepository(org.apache.bcel.util.Repository repository) {
        this.repository = repository;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public void setSuperClassName(ClassFileName superClassName) {
        this.superClassName = superClassName;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public String toString() {
        String access = Utility.accessToString(super.getAccessFlags(), true);
        access = access.isEmpty() ? "" : (access + " ");
        StringBuilder buf = new StringBuilder(128);
        buf.append(access).append(Utility.classOrInterface(super.getAccessFlags())).append(" ").append(className).append(" extends ").append(Utility.compactClassName(superClassName.getName(), false)).append('\n');
        int size = interfaces.length;
        if (size > 0) {
            buf.append("implements\t\t");
            for (int i = 0; i < size; i++) {
                buf.append(interfaceNames[i]);
                if (i < size - 1) {
                    buf.append(", ");
                }
            }
            buf.append('\n');
        }
        buf.append("file name\t\t").append(fileName).append('\n');
        buf.append("compiled from\t\t").append(sourceFileName).append('\n');
        buf.append("compiler version\t").append(version.getMajor()).append(".").append(version.getMinor()).append('\n');
        buf.append("access flags\t\t").append(super.getAccessFlags()).append('\n');
        buf.append("constant pool\t\t").append(constantPool.getLength()).append(" entries\n");
        buf.append("ACC_SUPER flag\t\t").append(isSuper()).append("\n");
        if (attributes.length > 0) {
            buf.append("\nAttribute(s):\n");
            for (Attribute attribute : attributes) {
                buf.append(indent(attribute));
            }
        }
        AnnotationEntry[] annotations = getAnnotationEntries();
        if (annotations != null && annotations.length > 0) {
            buf.append("\nAnnotation(s):\n");
            for (AnnotationEntry annotation : annotations) {
                buf.append(indent(annotation));
            }
        }
        if (fields.length > 0) {
            buf.append("\n").append(fields.length).append(" fields:\n");
            for (Field field : fields) {
                buf.append("\t").append(field).append('\n');
            }
        }
        if (methods.length > 0) {
            buf.append("\n").append(methods.length).append(" methods:\n");
            for (Method method : methods) {
                buf.append("\t").append(method).append('\n');
            }
        }
        return buf.toString();
    }

    static void Debug(String str) {
        if (debug) {
            System.out.println(str);
        }
    }

    private static String indent(Object obj) {
        StringTokenizer tok = new StringTokenizer(obj.toString(), "\n");
        StringBuilder buf = new StringBuilder();
        while (tok.hasMoreTokens()) {
            buf.append("\t").append(tok.nextToken()).append("\n");
        }
        return buf.toString();
    }
}
