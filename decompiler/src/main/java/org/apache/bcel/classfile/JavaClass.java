/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.bcel.classfile;

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

import org.apache.bcel.Const;
import org.apache.bcel.enums.Version;
import org.apache.bcel.generic.Type;
import org.apache.bcel.util.BCELComparator;
import org.apache.bcel.util.ClassQueue;
import org.apache.bcel.util.SyntheticRepository;

public class JavaClass extends AccessFlags implements Cloneable, Node, Comparable<JavaClass> {
    public static byte HEAP = 1;
    public static byte FILE = 2;
    public static byte ZIP = 3;
    private static boolean debug = Boolean.getBoolean("JavaClass.debug"); // Debugging on/off
    private static BCELComparator bcelComparator = new BCELComparator() {

        @Override
        public boolean equals(Object o1, Object o2) {
            JavaClass THIS = (JavaClass) o1;
            JavaClass THAT = (JavaClass) o2;
            return Objects.equals(THIS.getClassName(), THAT.getClassName());
        }

        @Override
        public int hashCode(Object o) {
            JavaClass THIS = (JavaClass) o;
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
    private Version version;
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
    private transient org.apache.bcel.util.Repository repository = SyntheticRepository.getInstance();

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, Version version, int access_flags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes) {
        this(classNameIndex, superclassNameIndex, fileName, version, access_flags, constantPool, interfaces, fields, methods, attributes, HEAP);
    }

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, Version version, int access_flags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte source) {
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
        // Get source file name if available
        for (Attribute attribute : attributes) {
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

    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(Visitor v) {
        v.visitJavaClass(this);
    }

    /**
     * Return the natural ordering of two JavaClasses. This ordering is based on the
     * class name
     *
     * @since 6.0
     */
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

    /**
     * @return deep copy of this class
     */
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
            // TODO should this throw?
        }
        return c;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeInt(Const.JVM_CLASSFILE_MAGIC);
        file.writeShort(version.getMinor());
        file.writeShort(version.getMajor());
        constantPool.dump(file);
        file.writeShort(super.getAccessFlags());
        file.writeShort(classNameIndex);
        file.writeShort(superclassNameIndex);
        file.writeShort(interfaces.length);
        for (int interface1 : interfaces) {
            file.writeShort(interface1);
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

    /**
     * Dump class to a file.
     *
     * @param file Output file
     * @throws IOException
     */
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

    /**
     * Dump Java class to output stream in binary format.
     *
     * @param file Output stream
     * @throws IOException
     */
    public void dump(OutputStream file) throws IOException {
        dump(new DataOutputStream(file));
    }

    /**
     * Dump class to a file named fileName.
     *
     * @param _file_name Output file name
     * @throws IOException
     */
    public void dump(String _file_name) throws IOException {
        dump(new File(_file_name));
    }

    /**
     * Return value as defined by given BCELComparator strategy. By default two
     * JavaClass objects are said to be equal when their class names are equal.
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    /**
     * Get all interfaces implemented by this JavaClass (transitively).
     */
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

    /**
     * @return Annotations on the class
     * @since 6.0
     */
    public AnnotationEntry[] getAnnotationEntries() {
        if (annotations == null) {
            annotations = AnnotationEntry.createAnnotationEntries(getAttributes());
        }

        return annotations;
    }

    /**
     * @return Attributes of the class.
     */
    public Attribute[] getAttributes() {
        return attributes;
    }

    /**
     * @return class in binary format
     */
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

    /**
     * @return Class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * @return Class name index.
     */
    public int getClassNameIndex() {
        return classNameIndex;
    }

    /**
     * @return Constant pool.
     */
    public ConstantPool getConstantPool() {
        return constantPool;
    }

    /**
     * @return Fields, i.e., variables of the class. Like the JVM spec mandates for
     *         the classfile format, these fields are those specific to this class,
     *         and not those of the superclass or superinterfaces.
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * @return File name of class, aka SourceFile attribute value
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return Indices in constant pool of implemented interfaces.
     */
    public int[] getInterfaceIndices() {
        return interfaces;
    }

    /**
     * @return Names of implemented interfaces.
     */
    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    /**
     * Get interfaces directly implemented by this JavaClass.
     */
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

    /**
     * @return the superclass for this JavaClass object, or null if this is
     *         java.lang.Object
     * @throws ClassNotFoundException if the superclass can't be found
     */
    public JavaClass getSuperClass() throws ClassNotFoundException {
        if ("java.lang.Object".equals(getClassName())) {
            return null;
        }
        return repository.loadClass(getSuperclassName());
    }

    /**
     * @return list of super classes of this class in ascending order, i.e.,
     *         java.lang.Object is always the last element
     * @throws ClassNotFoundException if any of the superclasses can't be found
     */
    public JavaClass[] getSuperClasses() throws ClassNotFoundException {
        JavaClass clazz = this;
        List<JavaClass> allSuperClasses = new ArrayList<>();
        for (clazz = clazz.getSuperClass(); clazz != null; clazz = clazz.getSuperClass()) {
            allSuperClasses.add(clazz);
        }
        return allSuperClasses.toArray(new JavaClass[allSuperClasses.size()]);
    }

    /**
     * returns the super class name of this class. In the case that this class is
     * java.lang.Object, it will return itself (java.lang.Object). This is probably
     * incorrect but isn't fixed at this time to not break existing clients.
     *
     * @return Superclass name.
     */
    public String getSuperclassName() {
        return superclassName;
    }

    /**
     * @return Class name index.
     */
    public int getSuperclassNameIndex() {
        return superclassNameIndex;
    }

    public Version getVersion() {
        return version;
    }

    /**
     * Return value as defined by given BCELComparator strategy. By default return
     * the hashcode of the class name.
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    /**
     * @return true, if this class is an implementation of interface inter
     * @throws ClassNotFoundException if superclasses or superinterfaces of this
     *                                class can't be found
     */
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

    /**
     * Equivalent to runtime "instanceof" operator.
     *
     * @return true if this JavaClass is derived from the super class
     * @throws ClassNotFoundException if superclasses or superinterfaces of this
     *                                object can't be found
     */
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

    /**
     * @since 6.0
     */
    public boolean isAnonymous() {
        computeNestedTypeStatus();
        return this.isAnonymous;
    }

    public boolean isClass() {
        return (super.getAccessFlags() & Const.ACC_INTERFACE) == 0;
    }

    /**
     * @since 6.0
     */
    public boolean isNested() {
        computeNestedTypeStatus();
        return this.isNested;
    }

    public boolean isSuper() {
        return (super.getAccessFlags() & Const.ACC_SUPER) != 0;
    }

    /**
     * @param attributes .
     */
    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    /**
     * @param className .
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @param classNameIndex .
     */
    public void setClassNameIndex(int classNameIndex) {
        this.classNameIndex = classNameIndex;
    }

    /**
     * @param constantPool .
     */
    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    /**
     * @param fields .
     */
    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    /**
     * Set File name of class, aka SourceFile attribute value
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @param interfaceNames .
     */
    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    /**
     * @param interfaces .
     */
    public void setInterfaces(int[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    public void setRepository(org.apache.bcel.util.Repository repository) { // TODO make protected?
        this.repository = repository;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public void setSuperclassName(String superclassName) {
        this.superclassName = superclassName;
    }

    /**
     * @param superclassNameIndex .
     */
    public void setSuperclassNameIndex(int superclassNameIndex) {
        this.superclassNameIndex = superclassNameIndex;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * @return String representing class contents.
     */
    @Override
    public String toString() {
        String access = Utility.accessToString(super.getAccessFlags(), true);
        access = access.isEmpty() ? "" : (access + " ");
        StringBuilder buf = new StringBuilder(128);
        buf.append(access).append(Utility.classOrInterface(super.getAccessFlags())).append(" ").append(className).append(" extends ").append(Utility.compactClassName(superclassName, false)).append('\n');
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

    /*
     * Print debug information depending on `JavaClass.debug'
     */
    static void Debug(String str) {
        if (debug) {
            System.out.println(str);
        }
    }

    /**
     * @return Comparison strategy object
     */
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

    /**
     * @param comparator Comparison strategy object
     */
    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
