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
package org.apache.bcel.generic.gen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.bcel.ClassFileName;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.AccessFlags;
import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.Annotations;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.RuntimeInvisibleAnnotations;
import org.apache.bcel.classfile.RuntimeVisibleAnnotations;
import org.apache.bcel.classfile.SourceFile;
import org.apache.bcel.enums.Version;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.ClassObserver;
import org.apache.bcel.generic.control.InstructionConst;
import org.apache.bcel.generic.control.InstructionList;
import org.apache.bcel.util.BCELComparator;
import org.apache.bcel.util.ClassAccessFlagsList;

/**
 * Template class for building up a java class. May be initialized with an
 * existing java class (file).
 *
 * @see JavaClass
 */
public class ClassGen extends AccessFlags implements Cloneable {

    private static BCELComparator bcelComparator = new BCELComparator() {

        @Override
        public boolean equals(Object o1, Object o2) {
            ClassGen THIS = (ClassGen) o1;
            ClassGen THAT = (ClassGen) o2;
            return Objects.equals(THIS.getClassName(), THAT.getClassName());
        }

        @Override
        public int hashCode(Object o) {
            ClassGen THIS = (ClassGen) o;
            return THIS.getClassName().hashCode();
        }
    };
    private ClassFileName superClassName;
    private String fileName;
    private ClassFileName className;
    private ConstantPoolGen cp;
    private List<Field> fieldList = new ArrayList<>();
    private List<Method> methodList = new ArrayList<>();
    private List<Attribute> attributeList = new ArrayList<>();
    private List<ClassFileName> interfaceList = new ArrayList<>();
    private List<AnnotationEntryGen> annotationList = new ArrayList<>();
    private List<ClassObserver> observers;
    private Version version = Version.Version_1_1;

    public ClassGen(ClassFileName className, ClassFileName superClassName, String fileName, int accessFlags, ClassFileName[] interfaces) {
        this(className, superClassName, fileName, accessFlags, interfaces, new ConstantPoolGen());
    }

    public ClassGen(ClassFileName className, ClassFileName superClassName, String fileName, int accessFlags, ClassFileName[] interfaces, ConstantPoolGen cp) {
        super(accessFlags);
        this.className = className;
        this.superClassName = superClassName;
        this.fileName = fileName;
        this.cp = cp;
        if (fileName != null) {
            addAttribute(new SourceFile(cp.addUtf8("SourceFile"), 2, cp.addUtf8(fileName), cp.getConstantPool()));
        }
        // classNameIndex = cp.addClass(className);
        // superclass_name_index = cp.addClass(superClassName.getName());
        if (interfaces != null) {
            for (ClassFileName interface1 : interfaces) {
                addInterface(interface1);
            }
        }
    }

    public ClassGen(JavaClass clazz) {
        super(clazz.getAccessFlags());
        className = clazz.getClassName();
        // superclass_name_index = clazz.getSuperClassName().getNameIndex()
        // className = clazz.getClassName().getName();
        superClassName = clazz.getSuperClassName();
        fileName = clazz.getSourceFileName();
        cp = new ConstantPoolGen(clazz.getConstantPool());
        version = clazz.getVersion();
        Attribute[] attributes = clazz.getAttributes();
        // J5TODO: Could make unpacking lazy, done on first reference
        AnnotationEntryGen[] annotations = unpackAnnotations(attributes);
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getFields();
        ClassFileName[] interfaces = clazz.getInterfaces();
        for (ClassFileName interface1 : interfaces) {
            addInterface(interface1);
        }
        for (Attribute attribute : attributes) {
            if (!(attribute instanceof Annotations)) {
                addAttribute(attribute);
            }
        }
        for (AnnotationEntryGen annotation : annotations) {
            addAnnotationEntry(annotation);
        }
        for (Method method : methods) {
            addMethod(method);
        }
        for (Field field : fields) {
            addField(field);
        }
    }

    public void addAnnotationEntry(AnnotationEntryGen a) {
        annotationList.add(a);
    }

    /**
     * Add an attribute to this class.
     *
     * @param a attribute to add
     */
    public void addAttribute(Attribute a) {
        attributeList.add(a);
    }

    public void addEmptyConstructor(int access_flags) {
        InstructionList il = new InstructionList();
        il.append(InstructionConst.THIS); // Push `this'
        il.append(new INVOKESPECIAL(cp.addMethodref(superClassName.getName(), "<init>", "()V")));
        il.append(InstructionConst.RETURN);
        MethodGen mg = new MethodGen(access_flags, Type.VOID, Type.NO_ARGS, null, "<init>", className, il, cp);
        mg.setMaxStack(1);
        addMethod(mg.getMethod());
    }

    public void addField(Field f) {
        fieldList.add(f);
    }

    public void addInterface(ClassFileName name) {
        interfaceList.add(name);
    }

    /**
     * Add a method to this class.
     *
     * @param m method to add
     */
    public void addMethod(Method m) {
        methodList.add(m);
    }

    /**
     * Add observer for this object.
     */
    public void addObserver(ClassObserver o) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(o);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }

    public boolean containsField(Field f) {
        return fieldList.contains(f);
    }

    /**
     * @return field object with given name, or null
     */
    public Field containsField(String name) {
        for (Field f : fieldList) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    /**
     * @return method object with given name and signature, or null
     */
    public Method containsMethod(String name, String signature) {
        for (Method m : methodList) {
            if (m.getName().equals(name) && m.getSignature().equals(signature)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Return value as defined by given BCELComparator strategy. By default two
     * ClassGen objects are said to be equal when their class names are equal.
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    // J5TODO: Should we make calling unpackAnnotations() lazy and put it in here?
    public AnnotationEntryGen[] getAnnotationEntries() {
        return annotationList.toArray(new AnnotationEntryGen[annotationList.size()]);
    }

    public Attribute[] getAttributes() {
        return attributeList.toArray(new Attribute[attributeList.size()]);
    }

    public ClassFileName getClassName() {
        return className;
    }

    public ConstantPoolGen getConstantPool() {
        return cp;
    }

    public Field[] getFields() {
        return fieldList.toArray(new Field[fieldList.size()]);
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getInterfaceNames() {
        int size = interfaceList.size();
        String[] interfaces = new String[size];
        for (int i = 0; i < size; i++) {
            interfaces[i] = interfaceList.get(i).getName();
        }
        return interfaces;
    }

    public int[] getInterfacesIndexes() {
        int size = interfaceList.size();
        int[] interfaces = new int[size];
        for (int i = 0; i < size; i++) {
            interfaces[i] = interfaceList.get(i).getNameIndex();
        }
        return interfaces;
    }

    public JavaClass getJavaClass() throws IOException {
        ClassFileName[] interfaces = interfaceList.toArray(new ClassFileName[interfaceList.size()]);
        Field[] fields = getFields();
        Method[] methods = getMethods();
        Attribute[] attributes = null;
        if (annotationList.isEmpty()) {
            attributes = getAttributes();
        } else {
            Attribute[] annAttributes = AnnotationEntryGen.getAnnotationAttributes(cp, getAnnotationEntries());
            attributes = new Attribute[attributeList.size() + annAttributes.length];
            attributeList.toArray(attributes);
            System.arraycopy(annAttributes, 0, attributes, attributeList.size(), annAttributes.length);
        }
        ConstantPool _cp = this.cp.getConstantPool();
        ClassAccessFlagsList accessFlags = new ClassAccessFlagsList(super.getAccessFlags());
        return new JavaClass(className, superClassName, fileName, version, accessFlags, _cp, interfaces, fields, methods, attributes);
    }

    public Method getMethodAt(int pos) {
        return methodList.get(pos);
    }

    public Method[] getMethods() {
        return methodList.toArray(new Method[methodList.size()]);
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
     * Remove an attribute from this class.
     *
     * @param a attribute to remove
     */
    public void removeAttribute(Attribute a) {
        attributeList.remove(a);
    }

    /**
     * Remove a field to this class.
     *
     * @param f field to remove
     */
    public void removeField(Field f) {
        fieldList.remove(f);
    }

    /**
     * Remove an interface from this class.
     *
     * @param name interface to remove (fully qualified name)
     */
    public void removeInterface(String name) {
        interfaceList.remove(name);
    }

    /**
     * Remove a method from this class.
     *
     * @param m method to remove
     */
    public void removeMethod(Method m) {
        methodList.remove(m);
    }

    /**
     * Remove observer for this object.
     */
    public void removeObserver(ClassObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

    /**
     * Replace given field with new one. If the old one does not exist add the new_
     * field to the class anyway.
     */
    public void replaceField(Field old, Field new_) {
        if (new_ == null) {
            throw new ClassGenException("Replacement method must not be null");
        }
        int i = fieldList.indexOf(old);
        if (i < 0) {
            fieldList.add(new_);
        } else {
            fieldList.set(i, new_);
        }
    }

    /**
     * Replace given method with new one. If the old one does not exist add the new_
     * method to the class anyway.
     */
    public void replaceMethod(Method old, Method new_) {
        if (new_ == null) {
            throw new ClassGenException("Replacement method must not be null");
        }
        int i = methodList.indexOf(old);
        if (i < 0) {
            methodList.add(new_);
        } else {
            methodList.set(i, new_);
        }
    }

    public void setClassName(ClassFileName className) {
        this.className = className;
    }

    public void setClassName(int class_name_index) {
        className.setNameIndex(class_name_index);
        className.setName(cp.getConstantPool().getConstantString(class_name_index, Const.CONSTANT_Class).replace('/', '.'));
    }

    public void setClassName(String name) {
        className.setName(name.replace('/', '.'));
        className.setNameIndex(cp.addClass(name));
    }

    public void setConstantPool(ConstantPoolGen constant_pool) {
        cp = constant_pool;
    }

    public void setMethodAt(Method method, int pos) {
        methodList.set(pos, method);
    }

    public void setMethods(Method[] methods) {
        methodList.clear();
        for (Method method : methods) {
            addMethod(method);
        }
    }

    public void setSuperclassName(int superclass_name_index) {
        superClassName.setNameIndex(superclass_name_index);
        superClassName.setName(cp.getConstantPool().getConstantString(superclass_name_index, Const.CONSTANT_Class).replace('/', '.'));
    }

    public void setSuperclassName(String name) {
        superClassName.setName(name.replace('/', '.'));
        superClassName.setNameIndex(cp.addClass(name));
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    private AnnotationEntryGen[] unpackAnnotations(Attribute[] attrs) {
        List<AnnotationEntryGen> annotationGenObjs = new ArrayList<>();
        for (Attribute attr : attrs) {
            if (attr instanceof RuntimeVisibleAnnotations) {
                RuntimeVisibleAnnotations rva = (RuntimeVisibleAnnotations) attr;
                AnnotationEntry[] annos = rva.getAnnotationEntries();
                for (AnnotationEntry a : annos) {
                    annotationGenObjs.add(new AnnotationEntryGen(a, getConstantPool(), false));
                }
            } else if (attr instanceof RuntimeInvisibleAnnotations) {
                RuntimeInvisibleAnnotations ria = (RuntimeInvisibleAnnotations) attr;
                AnnotationEntry[] annos = ria.getAnnotationEntries();
                for (AnnotationEntry a : annos) {
                    annotationGenObjs.add(new AnnotationEntryGen(a, getConstantPool(), false));
                }
            }
        }
        return annotationGenObjs.toArray(new AnnotationEntryGen[annotationGenObjs.size()]);
    }

    /**
     * Call notify() method on all observers. This method is not called
     * automatically whenever the state has changed, but has to be called by the
     * user after he has finished editing the object.
     */
    public void update() {
        if (observers != null) {
            for (ClassObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    /**
     * @return Comparison strategy object
     */
    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    /**
     * @param comparator Comparison strategy object
     */
    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
