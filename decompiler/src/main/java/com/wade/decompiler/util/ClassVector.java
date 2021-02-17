package com.wade.decompiler.util;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.JavaClass;

@SuppressWarnings("unused")
public class ClassVector implements java.io.Serializable {
    private static final long serialVersionUID = 5600397075672780806L;
    protected List<JavaClass> vec = new ArrayList<>();

    public void addElement(JavaClass clazz) {
        vec.add(clazz);
    }

    public JavaClass elementAt(int index) {
        return vec.get(index);
    }

    public void removeElementAt(int index) {
        vec.remove(index);
    }

    public JavaClass[] toArray() {
        JavaClass[] classes = new JavaClass[vec.size()];
        vec.toArray(classes);
        return classes;
    }
}
