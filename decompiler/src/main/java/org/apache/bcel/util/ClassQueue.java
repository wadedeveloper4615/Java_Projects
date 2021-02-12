package org.apache.bcel.util;

import java.util.LinkedList;

import org.apache.bcel.classfile.JavaClass;

public class ClassQueue {
    @Deprecated
    protected LinkedList<JavaClass> vec = new LinkedList<>(); // TODO not used externally

    public void enqueue(final JavaClass clazz) {
        vec.addLast(clazz);
    }

    public JavaClass dequeue() {
        return vec.removeFirst();
    }

    public boolean empty() {
        return vec.isEmpty();
    }

    @Override
    public String toString() {
        return vec.toString();
    }
}
