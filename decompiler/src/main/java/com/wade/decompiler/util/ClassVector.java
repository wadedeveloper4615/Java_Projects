package com.wade.decompiler.util;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.JavaClass;

@Deprecated
public class ClassVector implements java.io.Serializable {
    private static final long serialVersionUID = 5600397075672780806L;
    @Deprecated
    protected List<JavaClass> vec = new ArrayList<>();

    public void addElement(final JavaClass clazz) {
        vec.add(clazz);
    }

    public JavaClass elementAt(final int index) {
        return vec.get(index);
    }

    public void removeElementAt(final int index) {
        vec.remove(index);
    }

    public JavaClass[] toArray() {
        final JavaClass[] classes = new JavaClass[vec.size()];
        vec.toArray(classes);
        return classes;
    }
}
