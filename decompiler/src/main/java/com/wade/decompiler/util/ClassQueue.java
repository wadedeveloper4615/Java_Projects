package com.wade.decompiler.util;

import java.util.LinkedList;

import com.wade.decompiler.classfile.JavaClass;

public class ClassQueue {
    @Deprecated
    protected LinkedList<JavaClass> vec = new LinkedList<>(); // TODO not used externally

    public JavaClass dequeue() {
        return vec.removeFirst();
    }

    public boolean empty() {
        return vec.isEmpty();
    }

    public void enqueue(JavaClass clazz) {
        vec.addLast(clazz);
    }

    @Override
    public String toString() {
        return vec.toString();
    }
}
