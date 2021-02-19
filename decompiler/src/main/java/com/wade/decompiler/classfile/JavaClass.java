package com.wade.decompiler.classfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.InnerClass;
import com.wade.decompiler.classfile.attribute.InnerClasses;
import com.wade.decompiler.classfile.attribute.SourceFile;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.comparators.JavClassComparator;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.BCELComparator;
import com.wade.decompiler.util.ClassQueue;
import com.wade.decompiler.util.SyntheticRepository;
import com.wade.decompiler.util.Utility;

public class JavaClass extends ClassAccessFlagsList implements Comparable<JavaClass> {
    public static byte HEAP = 1;
    public static byte FILE = 2;
    public static byte ZIP = 3;
    private static boolean debug = Boolean.getBoolean("JavaClass.debug"); // Debugging on/off
    private static BCELComparator bcelComparator = new JavClassComparator();
    private String fileName;
    private String packageName;
    private String sourceFileName = "<Unknown>";
    private int classNameIndex;
    private int superclassNameIndex;
    private String className;
    private String superclassName;
    private ConstantPool constantPool;
    private int[] interfaces;
    private String[] interfaceNames;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private AnnotationEntry[] annotations;
    private byte source = HEAP;
    private boolean isAnonymous = false;
    private boolean isNested = false;
    private boolean computedNestedTypeStatus = false;
    private transient com.wade.decompiler.util.Repository repository = SyntheticRepository.getInstance();
    private Version version;

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, Version version, ClassAccessFlagsList access_flags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes) {
        this(classNameIndex, superclassNameIndex, fileName, version, access_flags, constantPool, interfaces, fields, methods, attributes, HEAP);
    }

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, Version version, ClassAccessFlagsList access_flags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte source) {
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
                sourceFileName = ((SourceFile) attribute).getSourceFile();
                break;
            }
        }
        className = constantPool.getConstantString(classNameIndex, ClassFileConstants.CONSTANT_Class);
        className = Utility.compactClassName(className, false);
        int index = className.lastIndexOf('.');
        if (index < 0) {
            packageName = "";
        } else {
            packageName = className.substring(0, index);
        }
        if (superclassNameIndex > 0) {
            superclassName = constantPool.getConstantString(superclassNameIndex, ClassFileConstants.CONSTANT_Class);
            superclassName = Utility.compactClassName(superclassName, false);
        } else {
            superclassName = "java.lang.Object";
        }
        interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            String str = constantPool.getConstantString(interfaces[i], ClassFileConstants.CONSTANT_Class);
            interfaceNames[i] = Utility.compactClassName(str, false);
        }
    }

    @Override
    public int compareTo(JavaClass obj) {
        return getClassName().compareTo(obj.getClassName());
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
                    String inner_class_name = constantPool.getConstantString(innerClasse.getInnerClassIndex(), ClassFileConstants.CONSTANT_Class);
                    inner_class_name = Utility.compactClassName(inner_class_name, false);
                    if (inner_class_name.equals(getClassName())) {
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
            JavaClass[] _interfaces = clazz.getInterfaces();
            if (clazz.isInterface()) {
                allInterfaces.add(clazz);
            } else if (souper != null) {
                queue.enqueue(souper);
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

    public int[] getInterfaceIndices() {
        return interfaces;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public JavaClass[] getInterfaces() throws ClassNotFoundException {
        String[] _interfaces = getInterfaceNames();
        JavaClass[] classes = new JavaClass[_interfaces.length];
        for (int i = 0; i < _interfaces.length; i++) {
            classes[i] = repository.loadClass(_interfaces[i]);
        }
        return classes;
    }

    public Method getMethod(java.lang.reflect.Method m) {
        for (Method method : methods) {
            if (m.getName().equals(method.getName()) && (m.getModifiers() == method.getFlags()) && Type.getSignature(m).equals(method.getSignature())) {
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

    public com.wade.decompiler.util.Repository getRepository() {
        return repository;
    }

    public byte getSource() {
        return source;
    }

    public String getSourceFileName() {
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
        return !super.hasFlag(ClassAccessFlags.ACC_INTERFACE);
    }

    public boolean isNested() {
        computeNestedTypeStatus();
        return this.isNested;
    }

    @Override
    public boolean isSuper() {
        return super.hasFlag(ClassAccessFlags.ACC_SUPER);
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassNameIndex(int classNameIndex) {
        this.classNameIndex = classNameIndex;
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

    public void setInterfaces(int[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    public void setRepository(com.wade.decompiler.util.Repository repository) {
        this.repository = repository;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public void setSuperclassName(String superclassName) {
        this.superclassName = superclassName;
    }

    public void setSuperclassNameIndex(int superclassNameIndex) {
        this.superclassNameIndex = superclassNameIndex;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public String toString() {
        String access = Utility.accessToString(new ClassAccessFlagsList(super.getFlags()), true);
        access = access.isEmpty() ? "" : (access + " ");
        StringBuilder buf = new StringBuilder(128);
        buf.append(access).append(Utility.classOrInterface(new ClassAccessFlagsList(super.getFlags()))).append(" ").append(className).append(" extends ").append(Utility.compactClassName(superclassName, false)).append('\n');
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
        buf.append("access flags\t\t").append(super.getFlags()).append('\n');
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

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    private static String indent(Object obj) {
        StringTokenizer tok = new StringTokenizer(obj.toString(), "\n");
        StringBuilder buf = new StringBuilder();
        while (tok.hasMoreTokens()) {
            buf.append("\t").append(tok.nextToken()).append("\n");
        }
        return buf.toString();
    }

    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
