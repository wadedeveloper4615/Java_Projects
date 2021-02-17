package com.wade.decompiler.generic.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.wade.decompiler.classfile.AnnotationEntry;
import com.wade.decompiler.classfile.Annotations;
import com.wade.decompiler.classfile.Attribute;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.RuntimeInvisibleAnnotations;
import com.wade.decompiler.classfile.RuntimeVisibleAnnotations;
import com.wade.decompiler.classfile.SourceFile;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;
import com.wade.decompiler.generic.INVOKESPECIAL;
import com.wade.decompiler.generic.base.InstructionConst;
import com.wade.decompiler.generic.base.InstructionList;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.BCELComparator;

public class ClassGen extends ClassAccessFlagsList implements Cloneable {
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
    private String className;
    private String superClassName;
    private String fileName;
    private int classNameIndex = -1;
    private int superclass_name_index = -1;
    private Version version = Version.Version_1_1;
    private ConstantPoolGen cp; // Template for building up constant pool
    // ArrayLists instead of arrays to gather fields, methods, etc.
    private List<Field> fieldList = new ArrayList<>();
    private List<Method> methodList = new ArrayList<>();
    private List<Attribute> attributeList = new ArrayList<>();
    private List<String> interfaceList = new ArrayList<>();
    private List<AnnotationEntryGen> annotationList = new ArrayList<>();
    private List<ClassObserver> observers;

    public ClassGen(JavaClass clazz) {
        super(clazz.getFlags());
        classNameIndex = clazz.getClassNameIndex();
        superclass_name_index = clazz.getSuperclassNameIndex();
        className = clazz.getClassName();
        superClassName = clazz.getSuperclassName();
        fileName = clazz.getSourceFileName();
        cp = new ConstantPoolGen(clazz.getConstantPool());
        version = clazz.getVersion();
        Attribute[] attributes = clazz.getAttributes();
        // J5TODO: Could make unpacking lazy, done on first reference
        AnnotationEntryGen[] annotations = unpackAnnotations(attributes);
        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getFields();
        String[] interfaces = clazz.getInterfaceNames();
        for (String interface1 : interfaces) {
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

    public ClassGen(String className, String superClassName, String fileName, int accessFlags, String[] interfaces) {
        this(className, superClassName, fileName, accessFlags, interfaces, new ConstantPoolGen());
    }

    public ClassGen(String className, String superClassName, String fileName, int accessFlags, String[] interfaces, ConstantPoolGen cp) {
        super(accessFlags);
        this.className = className;
        this.superClassName = superClassName;
        this.fileName = fileName;
        this.cp = cp;
        // Put everything needed by default into the constant pool and the vectors
        if (fileName != null) {
            addAttribute(new SourceFile(cp.addUtf8("SourceFile"), 2, cp.addUtf8(fileName), cp.getConstantPool()));
        }
        classNameIndex = cp.addClass(className);
        superclass_name_index = cp.addClass(superClassName);
        if (interfaces != null) {
            for (String interface1 : interfaces) {
                addInterface(interface1);
            }
        }
    }

    public void addAnnotationEntry(AnnotationEntryGen a) {
        annotationList.add(a);
    }

    public void addAttribute(Attribute a) {
        attributeList.add(a);
    }

    public void addEmptyConstructor(int access_flags) {
        InstructionList il = new InstructionList();
        il.append(InstructionConst.THIS); // Push `this'
        il.append(new INVOKESPECIAL(cp.addMethodref(superClassName, "<init>", "()V")));
        il.append(InstructionConst.RETURN);
        MethodGen mg = new MethodGen(access_flags, Type.VOID, Type.NO_ARGS, null, "<init>", className, il, cp);
        mg.setMaxStack(1);
        addMethod(mg.getMethod());
    }

    public void addField(Field f) {
        fieldList.add(f);
    }

    public void addInterface(String name) {
        interfaceList.add(name);
    }

    public void addMethod(Method m) {
        methodList.add(m);
    }

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

    public Field containsField(String name) {
        for (Field f : fieldList) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    public Method containsMethod(String name, String signature) {
        for (Method m : methodList) {
            if (m.getName().equals(name) && m.getSignature().equals(signature)) {
                return m;
            }
        }
        return null;
    }

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

    public String getClassName() {
        return className;
    }

    public int getClassNameIndex() {
        return classNameIndex;
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
        interfaceList.toArray(interfaces);
        return interfaces;
    }

    public int[] getInterfaces() {
        int size = interfaceList.size();
        int[] interfaces = new int[size];
        for (int i = 0; i < size; i++) {
            interfaces[i] = cp.addClass(interfaceList.get(i));
        }
        return interfaces;
    }

    public JavaClass getJavaClass() {
        int[] interfaces = getInterfaces();
        Field[] fields = getFields();
        Method[] methods = getMethods();
        Attribute[] attributes = null;
        if (annotationList.isEmpty()) {
            attributes = getAttributes();
        } else {
            // TODO: Sometime later, trash any attributes called 'RuntimeVisibleAnnotations'
            // or 'RuntimeInvisibleAnnotations'
            Attribute[] annAttributes = AnnotationEntryGen.getAnnotationAttributes(cp, getAnnotationEntries());
            attributes = new Attribute[attributeList.size() + annAttributes.length];
            attributeList.toArray(attributes);
            System.arraycopy(annAttributes, 0, attributes, attributeList.size(), annAttributes.length);
        }
        // Must be last since the above calls may still add something to it
        ConstantPool _cp = this.cp.getConstantPool();
        return new JavaClass(classNameIndex, superclass_name_index, fileName, version, new ClassAccessFlagsList(super.getFlags()), _cp, interfaces, fields, methods, attributes);
    }

    public Method getMethodAt(int pos) {
        return methodList.get(pos);
    }

    public Method[] getMethods() {
        return methodList.toArray(new Method[methodList.size()]);
    }

    public String getSuperclassName() {
        return superClassName;
    }

    public int getSuperclassNameIndex() {
        return superclass_name_index;
    }

    public Version getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public void removeAttribute(Attribute a) {
        attributeList.remove(a);
    }

    public void removeField(Field f) {
        fieldList.remove(f);
    }

    public void removeInterface(String name) {
        interfaceList.remove(name);
    }

    public void removeMethod(Method m) {
        methodList.remove(m);
    }

    public void removeObserver(ClassObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

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

    public void setClassName(String name) {
        className = name.replace('/', '.');
        classNameIndex = cp.addClass(name);
    }

    public void setClassNameIndex(int class_name_index) {
        this.classNameIndex = class_name_index;
        className = cp.getConstantPool().getConstantString(class_name_index, ClassFileConstants.CONSTANT_Class).replace('/', '.');
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

    public void setSuperclassName(String name) {
        superClassName = name.replace('/', '.');
        superclass_name_index = cp.addClass(name);
    }

    public void setSuperclassNameIndex(int superclass_name_index) {
        this.superclass_name_index = superclass_name_index;
        superClassName = cp.getConstantPool().getConstantString(superclass_name_index, ClassFileConstants.CONSTANT_Class).replace('/', '.');
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

    public void update() {
        if (observers != null) {
            for (ClassObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
