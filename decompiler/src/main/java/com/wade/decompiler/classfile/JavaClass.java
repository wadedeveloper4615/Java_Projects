package com.wade.decompiler.classfile;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.Type;
import com.wade.decompiler.util.BCELComparator;
import com.wade.decompiler.util.ClassQueue;
import com.wade.decompiler.util.SyntheticRepository;

public class JavaClass extends AccessFlags implements Cloneable, Node, Comparable<JavaClass> {
    public static final byte HEAP = 1;
    public static final byte FILE = 2;
    public static final byte ZIP = 3;
    private static final boolean debug = Boolean.getBoolean("JavaClass.debug"); // Debugging on/off
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(final Object o1, final Object o2) {
            final JavaClass THIS = (JavaClass) o1;
            final JavaClass THAT = (JavaClass) o2;
            return Objects.equals(THIS.getClassName(), THAT.getClassName());
        }

        @Override
        public int hashCode(final Object o) {
            final JavaClass THIS = (JavaClass) o;
            return THIS.getClassName().hashCode();
        }
    };
    private String fileName;
    private String packageName;
    private String sourceFileName = "<Unknown>";
    private int classNameIndex;
    private int superclassNameIndex;
    private String className;
    private String superclassName;
    private int major;
    private int minor; // Compiler version
    private ConstantPool constantPool; // Constant pool
    private int[] interfaces; // implemented interfaces
    private String[] interfaceNames;
    private Field[] fields; // Fields, i.e., variables of class
    private Method[] methods; // methods defined in the class
    private Attribute[] attributes; // attributes defined in the class
    private AnnotationEntry[] annotations; // annotations defined on the class
    private byte source = HEAP; // Generated in memory
    private boolean isAnonymous = false;
    private boolean isNested = false;
    private boolean computedNestedTypeStatus = false;
    private transient com.wade.decompiler.util.Repository repository = SyntheticRepository.getInstance();

    public JavaClass(final int classNameIndex, final int superclassNameIndex, final String fileName, final int major, final int minor, final int access_flags, final ConstantPool constantPool, final int[] interfaces, final Field[] fields, final Method[] methods, final Attribute[] attributes) {
        this(classNameIndex, superclassNameIndex, fileName, major, minor, access_flags, constantPool, interfaces, fields, methods, attributes, HEAP);
    }

    public JavaClass(final int classNameIndex, final int superclassNameIndex, final String fileName, final int major, final int minor, final int access_flags, final ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, final byte source) {
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
    public void accept(final Visitor v) {
        v.visitJavaClass(this);
    }

    @Override
    public int compareTo(final JavaClass obj) {
        return getClassName().compareTo(obj.getClassName());
    }

    private void computeNestedTypeStatus() {
        if (computedNestedTypeStatus) {
            return;
        }
        for (final Attribute attribute : this.attributes) {
            if (attribute instanceof InnerClasses) {
                final InnerClass[] innerClasses = ((InnerClasses) attribute).getInnerClasses();
                for (final InnerClass innerClasse : innerClasses) {
                    boolean innerClassAttributeRefersToMe = false;
                    String inner_class_name = constantPool.getConstantString(innerClasse.getInnerClassIndex(), Const.CONSTANT_Class);
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
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return c;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeInt(Const.JVM_CLASSFILE_MAGIC);
        file.writeShort(minor);
        file.writeShort(major);
        constantPool.dump(file);
        file.writeShort(super.getAccessFlags());
        file.writeShort(classNameIndex);
        file.writeShort(superclassNameIndex);
        file.writeShort(interfaces.length);
        for (final int interface1 : interfaces) {
            file.writeShort(interface1);
        }
        file.writeShort(fields.length);
        for (final Field field : fields) {
            field.dump(file);
        }
        file.writeShort(methods.length);
        for (final Method method : methods) {
            method.dump(file);
        }
        if (attributes != null) {
            file.writeShort(attributes.length);
            for (final Attribute attribute : attributes) {
                attribute.dump(file);
            }
        } else {
            file.writeShort(0);
        }
        file.flush();
    }

    public void dump(final File file) throws IOException {
        final String parent = file.getParent();
        if (parent != null) {
            final File dir = new File(parent);
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

    public void dump(final OutputStream file) throws IOException {
        dump(new DataOutputStream(file));
    }

    public void dump(final String _file_name) throws IOException {
        dump(new File(_file_name));
    }

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public JavaClass[] getAllInterfaces() throws ClassNotFoundException {
        final ClassQueue queue = new ClassQueue();
        final Set<JavaClass> allInterfaces = new TreeSet<>();
        queue.enqueue(this);
        while (!queue.empty()) {
            final JavaClass clazz = queue.dequeue();
            final JavaClass souper = clazz.getSuperClass();
            final JavaClass[] _interfaces = clazz.getInterfaces();
            if (clazz.isInterface()) {
                allInterfaces.add(clazz);
            } else if (souper != null) {
                queue.enqueue(souper);
            }
            for (final JavaClass _interface : _interfaces) {
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
        final ByteArrayOutputStream s = new ByteArrayOutputStream();
        final DataOutputStream ds = new DataOutputStream(s);
        try {
            dump(ds);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ds.close();
            } catch (final IOException e2) {
                e2.printStackTrace();
            }
        }
        return s.toByteArray();
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

    public Method getMethod(final java.lang.reflect.Method m) {
        for (final Method method : methods) {
            if (m.getName().equals(method.getName()) && (m.getModifiers() == method.getModifiers()) && Type.getSignature(m).equals(method.getSignature())) {
                return method;
            }
        }
        return null;
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

    public com.wade.decompiler.util.Repository getRepository() {
        return repository;
    }

    public final byte getSource() {
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

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
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

    public final boolean isAnonymous() {
        computeNestedTypeStatus();
        return this.isAnonymous;
    }

    public final boolean isClass() {
        return (super.getAccessFlags() & Const.ACC_INTERFACE) == 0;
    }

    public final boolean isNested() {
        computeNestedTypeStatus();
        return this.isNested;
    }

    public final boolean isSuper() {
        return (super.getAccessFlags() & Const.ACC_SUPER) != 0;
    }

    public void setAttributes(final Attribute[] attributes) {
        this.attributes = attributes;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public void setClassNameIndex(final int classNameIndex) {
        this.classNameIndex = classNameIndex;
    }

    public void setConstantPool(final ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setFields(final Field[] fields) {
        this.fields = fields;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setInterfaceNames(final String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public void setInterfaces(final int[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setMajor(final int major) {
        this.major = major;
    }

    public void setMethods(final Method[] methods) {
        this.methods = methods;
    }

    public void setMinor(final int minor) {
        this.minor = minor;
    }

    public void setRepository(final com.wade.decompiler.util.Repository repository) { // TODO make protected?
        this.repository = repository;
    }

    public void setSourceFileName(final String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public void setSuperclassName(final String superclassName) {
        this.superclassName = superclassName;
    }

    public void setSuperclassNameIndex(final int superclassNameIndex) {
        this.superclassNameIndex = superclassNameIndex;
    }

    @Override
    public String toString() {
        String access = Utility.accessToString(super.getAccessFlags(), true);
        access = access.isEmpty() ? "" : (access + " ");
        final StringBuilder buf = new StringBuilder(128);
        buf.append(access).append(Utility.classOrInterface(super.getAccessFlags())).append(" ").append(className).append(" extends ").append(Utility.compactClassName(superclassName, false)).append('\n');
        final int size = interfaces.length;
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
        buf.append("compiler version\t").append(major).append(".").append(minor).append('\n');
        buf.append("access flags\t\t").append(super.getAccessFlags()).append('\n');
        buf.append("constant pool\t\t").append(constantPool.getLength()).append(" entries\n");
        buf.append("ACC_SUPER flag\t\t").append(isSuper()).append("\n");
        if (attributes.length > 0) {
            buf.append("\nAttribute(s):\n");
            for (final Attribute attribute : attributes) {
                buf.append(indent(attribute));
            }
        }
        final AnnotationEntry[] annotations = getAnnotationEntries();
        if (annotations != null && annotations.length > 0) {
            buf.append("\nAnnotation(s):\n");
            for (final AnnotationEntry annotation : annotations) {
                buf.append(indent(annotation));
            }
        }
        if (fields.length > 0) {
            buf.append("\n").append(fields.length).append(" fields:\n");
            for (final Field field : fields) {
                buf.append("\t").append(field).append('\n');
            }
        }
        if (methods.length > 0) {
            buf.append("\n").append(methods.length).append(" methods:\n");
            for (final Method method : methods) {
                buf.append("\t").append(method).append('\n');
            }
        }
        return buf.toString();
    }

    static void Debug(final String str) {
        if (debug) {
            System.out.println(str);
        }
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    private static String indent(final Object obj) {
        final StringTokenizer tok = new StringTokenizer(obj.toString(), "\n");
        final StringBuilder buf = new StringBuilder();
        while (tok.hasMoreTokens()) {
            buf.append("\t").append(tok.nextToken()).append("\n");
        }
        return buf.toString();
    }

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
