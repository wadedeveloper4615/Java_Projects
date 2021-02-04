package com.wade.app.util;

import java.util.LinkedList;

import com.wade.app.JavaClass;

public class ClassQueue {
    protected LinkedList<JavaClass> vec = new LinkedList<>();

    public JavaClass dequeue() {
        return vec.removeFirst();
    }

    public boolean empty() {
        return vec.isEmpty();
    }

    public void enqueue(final JavaClass clazz) {
        vec.addLast(clazz);
    }

    @Override
    public String toString() {
        return vec.toString();
    }
}
